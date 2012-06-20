/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.perfectlibrary.business;

import edu.perfectlibrary.common.UserNotFoundException;
import edu.perfectlibrary.model.library.Issue;
import edu.perfectlibrary.model.library.LibraryDocument;
import edu.perfectlibrary.model.library.Reservation;
import edu.perfectlibrary.model.library.Review;
import edu.perfectlibrary.model.social.Account;
import edu.perfectlibrary.model.social.MemberAccount;
import edu.perfectlibrary.model.social.Person;
import java.util.Date;
import java.util.List;
import javax.ejb.*;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

/**
 *
 * @author Užívateľ
 */
@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class MemberAccountService implements MemberAccountServiceLocal {

    @PersistenceContext(unitName = "PerfectLibrary5-ejbPU", type = PersistenceContextType.TRANSACTION)
    EntityManager em;
    @EJB
    AccountServiceLocal accountService;
    @EJB
    LibraryIssueServiceLocal libraryIssueService;

    @Override
    public long getAccountIdByUsername(String username) throws UserNotFoundException {
        Account account = null;
        try {
            account = (Account) em.createQuery("SELECT m FROM MemberAccount m WHERE m.username=:username").setParameter("username", username).getSingleResult();
        } catch (NoResultException e) {
            throw new UserNotFoundException();
        }
        return account.getAccountId();
    }
    @Override
    public long getAccountIdByFullname(String first, String second) throws UserNotFoundException {
        Person person = null;
        MemberAccount account = null;
        try {
            person = (Person) em.createQuery("SELECT p FROM Person p WHERE p.firstname=:first AND p.surname=:second").setParameter("first", first).setParameter("second", second).getSingleResult();
            account = (MemberAccount) em.createQuery("SELECT m FROM MemberAccount m WHERE m.person=:person").setParameter("person", person).getSingleResult();
        } catch (NoResultException e) {
            throw new UserNotFoundException();
        }
        return account.getAccountId();
    }

    @Override
    public MemberAccount findMemberAccoundById(long id) {
        Account retrieved = null;
        MemberAccount memberAccount = null;
        try {
            retrieved = accountService.getAccount(id);
            memberAccount = (MemberAccount) retrieved;
        } catch (ClassCastException e) {
            throw new RuntimeException("Invalid account type. Type [" + retrieved.getClass().getSimpleName() + "] cannot be proccessed by MemberAccountService.");
        }
        return memberAccount;
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public MemberAccount getMemberAccount(long id) {
        return findMemberAccoundById(id);
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public List<Reservation> getMemberAccountReservations(long id) {
        return findMemberAccoundById(id).getReservations();
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public List<Issue> getMemberAccountIssues(long id) {
        return findMemberAccoundById(id).getIssues();
    }

    @Override
    public List<Review> getAllReviewsByMember(MemberAccount member) {
        return (List<Review>) em.createQuery("SELECT r FROM Review r WHERE r.memberAccount=:member").setParameter("member", member).getResultList();
    }

    @Override
    public List<Issue> getAllIssuesByMember(MemberAccount member) {
        return (List<Issue>) em.createQuery("SELECT i FROM Issue i WHERE i.memberAccount=:member").setParameter("member", member).getResultList();
    }

    @Override
    public List<Reservation> getAllReservationByMember(MemberAccount member) {
        return (List<Reservation>) em.createQuery("SELECT i FROM Reservation i WHERE i.memberAccount=:member").setParameter("member", member).getResultList();
    }

    @Asynchronous // fire and forget
    private void createReview(String content, LibraryDocument document, MemberAccount account) {
        Review review = new Review();
        review.setContent(content);
        review.setCreated(new Date());
        review.setLibraryDocument(document);
        review.setMemberAccount(account);
        em.persist(review);
    }

    @Override
    public void processInsertReviewRequest(String content, LibraryDocument document, MemberAccount account) {
        createReview(content, document, account);
    }
    //cele zle
//    public List<Review> findReviewsByMember2(String clientInput) {
//        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
//        CriteriaQuery<Review> cq = criteriaBuilder.createQuery(Review.class);
//
//        Root<Review> rev = cq.from(Review.class);
//
//        cq.select(rev).where(criteriaBuilder.like(rev.get(Review_.memberAccount), clientInput.trim() + "%"));  // alebo ... +"*"));    ?
//        System.out.println(">>> result type: " + cq.getResultType());
////      
//        List<Review> searchOutputList = em.createQuery(cq).getResultList();
//
//        return searchOutputList;
//    }
}
