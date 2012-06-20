/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.perfectlibrary.transfer;

import edu.perfectlibrary.model.library.Issue;
import edu.perfectlibrary.model.library.LibraryDocument;
import edu.perfectlibrary.model.library.Review;
import edu.perfectlibrary.model.library.Penalty;
import edu.perfectlibrary.model.library.Reservation;
import edu.perfectlibrary.common.LibraryIssuePolicyViolationException;
import edu.perfectlibrary.common.NoItemsAvailableException;
import edu.perfectlibrary.common.OneIssuePerDocumentAllowedViolationException;
import edu.perfectlibrary.common.UserNotFoundException;
import edu.perfectlibrary.business.AccountServiceLocal;
import edu.perfectlibrary.business.LibraryIssueServiceLocal;
import edu.perfectlibrary.business.LibraryReservationServiceLocal;
import edu.perfectlibrary.business.MemberAccountServiceLocal;
import edu.perfectlibrary.model.social.Account;
import edu.perfectlibrary.model.social.MemberAccount;
import java.io.IOException;
import java.io.Serializable;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.persistence.PostLoad;
import javax.persistence.PostPersist;
import javax.persistence.PostUpdate;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Užívateľ
 */
public class MemberAccoundTransfer implements Serializable {

    @EJB
    AccountServiceLocal accountService;
    @EJB
    MemberAccountServiceLocal memberAccountService;
    @EJB
    LibraryIssueServiceLocal libraryIssueService;
    @EJB
    LibraryReservationServiceLocal libraryReservationService;
    MemberAccount memberAccount;
    String insertedUsername, insertedPassword;
    LibraryDocument selectedDocument;
    Reservation selectedReservation;
    Issue selectedIssue;
    List<Penalty> penalties;
    List<Review> reviews;
    List<Issue> issues;
    List<Reservation> reservations;
    String reviewInputTextArea;

    @PostUpdate
    public void postUpdateTest() {
        System.out.println(">>>>POST UPDATE CALLED.....");
    }

    @PostPersist
    public void postPersistTest() {
        System.out.println(">>>>POST PERSIST CALLED.....");
    }

    @PostLoad
    public void postLoadTest() {
        System.out.println(">>>>POST LOAD CALLED.....");
    }

    public AccountServiceLocal getAccountService() {
        return accountService;
    }

    public void setAccountService(AccountServiceLocal accountService) {
        this.accountService = accountService;
    }

    public MemberAccoundTransfer() {
    }

    public String getInsertedUsername() {
        return insertedUsername;
    }

    public void setInsertedUsername(String insertedUsername) {
        this.insertedUsername = insertedUsername;
    }

    public MemberAccount getMemberAccount() {
        return memberAccount;
    }

    public void setMemberAccount(MemberAccount memberAccount) {
        this.memberAccount = memberAccount;
    }

    public String getInsertedPassword() {
        return insertedPassword;
    }

    public void setInsertedPassword(String insertedPassword) {
        this.insertedPassword = insertedPassword;
    }

    public LibraryDocument getSelectedDocument() {
        return selectedDocument;
    }

    public void setSelectedDocument(LibraryDocument selectedDocument) {
        this.selectedDocument = selectedDocument;
    }

    public List<Penalty> getPenalties() {
        penalties = accountService.getPenaltiesByMemberAccount(memberAccount);
        return penalties;
    }

    public List<Review> getReviews() {
        reviews = memberAccountService.getAllReviewsByMember(memberAccount);
        return reviews;
    }

    public List<Issue> getIssues() {
        issues = accountService.getIssuesByMemberAccount(memberAccount);
//        issues = memberAccountService.getMemberAccountIssues(memberAccount.getAccountId());
//        issues = memberAccountService.getAllIssuesByMember(memberAccount);
        return issues;
    }

    public List<Reservation> getReservations() {
        reservations = memberAccountService.getAllReservationByMember(memberAccount);
        return reservations;
    }

    public String getReviewInputTextArea() {
        return reviewInputTextArea;
    }

    public void setReviewInputTextArea(String reviewInputTextArea) {
        this.reviewInputTextArea = reviewInputTextArea;
    }

    public String processReservationRemoveRequest(Reservation reservation) {
        selectedReservation = reservation;
        libraryReservationService.processReservationRemovingRequest(selectedReservation);
        return "reservations";
    }

    public String processIssueCancelationRequest(Issue issue) {
        selectedIssue = issue;
        libraryIssueService.processIssueCancelation(issue);
        return "issues";
    }

    public String processInsertReviewRequest() {
        memberAccountService.processInsertReviewRequest(reviewInputTextArea, selectedDocument, memberAccount);
        String outcome;
        outcome = "reviews";
        return outcome;
    }

    public String processIssueRequest(LibraryDocument document) {
        String outcome;
        FacesMessage msg = null;
        try {
            libraryIssueService.processIssueRequest(memberAccount, document);
            System.out.println(">>>>>>>>INFO: Issue request was succesfull.");
        } catch (NoItemsAvailableException e) {
            System.out.println(">>>>>>>>INFO: No items avaialabe. Issue request was NOT succesfull (NoItemsAvailableException thrown).");
            msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "No items avaliable by this document, sorry. Reservation was automatically created for you. You can view your reservation in your account", null);
            FacesContext.getCurrentInstance().addMessage(null, msg);
            outcome = "IssueRequestFAIL";
            return outcome;
        } catch (OneIssuePerDocumentAllowedViolationException f) {
            System.out.println(">>>>>>>>INFO: No items avaialabe. Issue request was NOT succesfull (OneIssuePerDocumentAllowedViolationException thrown).");
            msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "You have already issued this document, sorry.", null);
            FacesContext.getCurrentInstance().addMessage(null, msg);
            outcome = "IssueRequestFAIL";
            return outcome;
        } catch (LibraryIssuePolicyViolationException x) {
            System.out.println(">>>>>>>>INFO: No items avaialabe. Issue request was NOT succesfull (LibraryIssuePolicyViolationException thrown).");
            throw new RuntimeException("Subclass of LibraryIssuePolicyViolationException [" + x.getClass() + "] was declared and WAS thrown, which means some rule WAS violated, but was not catched in the appropriate place at [" + this.getClass() + "]. You need to provide a behaviour for specific case/subclass of LibraryIssuePolicyViolationException when is thrown (=corresponding rule is violated) at [" + this.getClass() + "].");
        }
        outcome = "IssueRequestOK";
        return "/faces/account/accountmain.xhtml?faces-redirect=true";
    }

    public String logUser() throws NoSuchAlgorithmException, IOException {
        Account account = null;
        boolean equal = false;
        try {
            account = accountService.getAccountByUsername(insertedUsername);
            memberAccount = (MemberAccount) account;
            if (insertedPassword == null) {
                insertedPassword = "";
            }
            equal = accountService.compare(insertedPassword, account);
        } catch (UserNotFoundException ignored) {
            System.out.println(">>>>exception ignored succesfully");

            // TIME-RESISTANT ATTACK
            // Computation time is equal to the time needed by a legitimate user
        }
        String outcome = null;
        FacesMessage msg = null;
        if (equal) {
            outcome = "success";
        } else {
            msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "The username or password you have entered is incorrect.", null);
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
        return outcome;
    }

    public String doLogout() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpSession httpSession = (HttpSession) facesContext.getExternalContext().getSession(false);
        httpSession.invalidate();
        memberAccount = null;   // GC
//        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "/faces/index.xhtml?faces-redirect=true";
    }
}
