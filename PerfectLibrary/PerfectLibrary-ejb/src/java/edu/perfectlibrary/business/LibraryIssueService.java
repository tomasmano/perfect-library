/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.perfectlibrary.business;

import edu.perfectlibrary.common.LibraryIssuePolicyViolationException;
import edu.perfectlibrary.common.NoItemsAvailableException;
import edu.perfectlibrary.business.confirmation.IssueConfirmationServiceLocal;
import edu.perfectlibrary.business.confirmation.PenaltyConfirmationServiceLocal;
import edu.perfectlibrary.events.LibraryServiceEventContext;
import edu.perfectlibrary.events.LibraryServiceEvent;
import edu.perfectlibrary.events.NewIssueEvent;
import edu.perfectlibrary.events.NewPenaltyEvent;
import edu.perfectlibrary.model.social.MemberAccount;
import edu.perfectlibrary.model.library.*;
import edu.perfectlibrary.policy.ServicePolicyRule;
import edu.perfectlibrary.policy.providers.LibraryIssuePolicyProviderLocal;
import java.util.*;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Užívateľ
 */
@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class LibraryIssueService implements LibraryIssueServiceLocal {
    
    public static Logger logger = LoggerFactory.getLogger(LibraryIssueService.class);
    @PersistenceContext(unitName = "PerfectLibrary5-ejbPU", type = PersistenceContextType.TRANSACTION)
    EntityManager em;
    @EJB
    MemberAccountServiceLocal memberAccountService;
    @EJB
    LibraryReservationServiceLocal libraryReservationService;
    @EJB
    LibraryIssuePolicyProviderLocal issuePolicyProvider;
    @EJB
    IssueConfirmationServiceLocal issueConfirmationService;
    @EJB
    PenaltyConfirmationServiceLocal penaltyConfirmationService;

    @Override
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public Issue getIssueById(long id) {
        return em.find(Issue.class, id);
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public List<Issue> getIssuesByMemberAccount(MemberAccount memberAccount) {
        return memberAccount.getIssues();
    }

    @Override
    public void updateIssue(Issue issue) {
        if (em.find(Issue.class, issue.getId()) == null) {
            throw new IllegalArgumentException("Cannot update issue. Unknown issue id: '" + issue.getId() + "'.");
        }
        em.merge(issue);
    }

    @Override
    public void proccesExpiredIssue(Issue expired) {
        expired.setPenalty(createNewPenalty(expired));
        updateIssue(expired);   // TODO unecessary statement? => @OneToOne(mappedBy="penalty", cascade= CascadeType.MERGE) => anotacia fieldu issue z class Penalty
        logger.info("Penalty for an account with a username '{}' has been created at {}.", expired.getMemberAccount().getUsername(), new Date());
    }

    @Override
    public List<Issue> getExpiredIssues() {
        Query q = em.createQuery("SELECT i FROM Issue i WHERE i.until<= :today");
        q.setParameter("today", new Date());
        List<Issue> expired = q.getResultList();
        return expired;
    }
    
    @Override
    public void handleExpiredIssuesRoutine(){
        List<Issue> expired = getExpiredIssues();
        logger.info("Number of expired issues: {}, processing them now ... ", expired.size());
        for (Issue issue : expired) {
            proccesExpiredIssue(issue);
        }
    }

    private Date provideCurrentDeadlineForIssues(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, 30);
        date = cal.getTime();
        return date;
    }

    private Penalty createNewPenalty(Issue expired) {
        Penalty penalty = new Penalty();
        penalty.setIssue(expired);
        em.persist(penalty);
        newPenaltyCreatedEvent(expired.getMemberAccount(), penalty);
        return penalty;
    }

    private Item findItem(Long id) {
        return em.find(Item.class, id);
    }

    private List<Item> getAvailableItemsByDocument(LibraryDocument document) {
        List<Item> availableItems = new ArrayList<Item>();
        List<Item> items = new ArrayList<Item>(document.getItems().values());
        for (Item item : items) {
            if (item.isIsAvailable()) {
                availableItems.add(item);
            }
        }
        return availableItems;
    }

    private void validateIssuePolicyViolation(MemberAccount account, LibraryDocument document) throws LibraryIssuePolicyViolationException {
        Set<ServicePolicyRule> rules = issuePolicyProvider.getPolicyRules();
        for (ServicePolicyRule issuePolicyRule : rules) {
            issuePolicyRule.validatePolicyViolation(account, document);
        }
    }
    
    private void newPenaltyCreatedEvent(MemberAccount account, Penalty penalty){
        LibraryServiceEventContext context = new LibraryServiceEventContext(account, penalty);
        LibraryServiceEvent event=new NewPenaltyEvent(context);
        penaltyConfirmationService.processConfirmationRequest(event);
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    private void newIssueCreatedEvent(MemberAccount account, Issue issue) {
        LibraryServiceEventContext context = new LibraryServiceEventContext(account, issue);
        LibraryServiceEvent event = new NewIssueEvent(context);
        issueConfirmationService.processConfirmationRequest(event);
    }

    private void issueCancelationEvent(Issue issue) {
        System.out.println("DEBUG>>>>> Issue cancelation occured on issue: " + issue.getItem().getLibraryDocument().getTitle());
        LibraryDocument document = issue.getItem().getLibraryDocument();
        List<Reservation> reservationsByDocument = document.getReservations();
        boolean noReservationAvailable = reservationsByDocument.isEmpty();
        System.out.println("DEBUG>>>> noReservationAvailabale should be false:" + noReservationAvailable);
        if (!noReservationAvailable) {
            Reservation reservation = reservationsByDocument.get(0);
            System.out.println("DEBUG>>>>>> issueCancelationEvent: "+reservation.getAccount().getUsername()+" "+reservation.getLibraryDocument().getTitle());
            libraryReservationService.processReservationRemovingRequest(reservation);
            MemberAccount member = reservation.getMemberAccount();
            try {
                processIssueRequest(member, document);
            } catch (NoItemsAvailableException e) {
                throw new RuntimeException("An exception occured that should never be thrown while proccesing new issue request after previos issue cancelation has finished and available reservation was found: " + e.getClass().getCanonicalName());
            } catch (LibraryIssuePolicyViolationException f) {
                throw new RuntimeException("An exception occured (signaling issue policy violation) when there is no reason to be thrown while proccesing new issue request after previos issue cancelation has succesfully finished and available reservation was found: " + f.getClass().getCanonicalName());
            }
        }
    }

    @Override
    public void processIssueRequest(MemberAccount member, LibraryDocument document) throws NoItemsAvailableException, LibraryIssuePolicyViolationException {
        List<Item> availableItems = getAvailableItemsByDocument(document);
        if (availableItems.isEmpty()) {
            libraryReservationService.processReservationRequest(member, document);
            throw new NoItemsAvailableException();
        }
        validateIssuePolicyViolation(member, document);
        Issue newIssue = createNewIssue(new Date(), member, availableItems.get(0));    // TODO is "new Date()" usage appropriate?
        newIssueCreatedEvent(member, newIssue);
    }

    @Override
    public void processIssueCancelation(Issue issue) {
        issue.getItem().setIsAvailable(true);
        updateIssue(issue);
//        em.remove(issue);
        issueCancelationEvent(issue);
        Issue removed = em.merge(issue);
        em.remove(removed);
    }

    private Issue createNewIssue(Date time, MemberAccount member, Item requested) {
        Item managed = em.merge(requested);
        managed.setIsAvailable(false);
        System.out.println(managed);
        em.merge(managed);
        Issue issue = new Issue();
        issue.setId((long) Math.random() * 1000);
        issue.setSince(time);
        Date currentReturnDeadline = provideCurrentDeadlineForIssues(time);
        issue.setUntil(currentReturnDeadline);
        issue.setMemberAccount(member);
        issue.setItem(managed); //TODO
        em.persist(issue);
        return issue;
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public int getAvaliableItemsSize(LibraryDocument document) {
        return getAvailableItemsByDocument(document).size();
    }
//    public List<Penalty> getPenalties(MemberAccount memberAccount) {
//        List<Penalty> penalties;
//        List<Issue> issues = getIssues(memberAccount);
//        for (Issue issue : issues) {
//            Penalty penalty = issue.getPenalty();
//            if (penalty!=null) {
//                penalties.add(penalty);
//            }
//        }
//        return penalties;
//    }
}
