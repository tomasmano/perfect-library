/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.perfectlibrary.business;

import edu.perfectlibrary.common.UserNotFoundException;
import edu.perfectlibrary.model.social.Account;
import edu.perfectlibrary.model.social.MemberAccount;
import edu.perfectlibrary.model.library.Issue;
import edu.perfectlibrary.model.library.Penalty;
import edu.perfectlibrary.model.library.Reservation;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.*;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

/**
 *
 * @author Užívateľ
 * 
 * Business object for ease of the future development mostly. See 'MemberAccountService' 
 * for relevant business logic.
 */
@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class AccountService implements AccountServiceLocal {

    @PersistenceContext(unitName = "PerfectLibrary5-ejbPU", type = PersistenceContextType.TRANSACTION)
    EntityManager em;
    @EJB
    EncryptionServiceLocal encryptionService;
    private static final String ACCOUNT_QUERY_HEAD = "SELECT a FROM MemberAccount a "; //TODO

    @Override
    public Account getAccount(long id) {
        return em.find(MemberAccount.class, id);
    }

//    @PostConstruct
//    public void initPostConstruct() {
//        System.out.println(">>> AccountService EJB, PostConstuct called: OK");
//    }
    // Java Persistance Query Langue example, using parameters with a Dynamic Query
    @Override
    public Account getAccountByUsername(String clientInput) throws UserNotFoundException {
        String query = ACCOUNT_QUERY_HEAD + "WHERE a.username = :clientInputParam";
        Account queried = null;
        try {
            queried = em.createQuery(query, Account.class).setParameter("clientInputParam", clientInput).getSingleResult();
        } catch (NoResultException ex) {
            throw new UserNotFoundException();
        }
        return queried;
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public List<Issue> getIssuesByMemberAccount(MemberAccount memberAccount) {
//        memberAccount = em.find(MemberAccount.class, memberAccount.getAccountId());
        memberAccount = em.merge(memberAccount);
        em.refresh(memberAccount);
        List<Issue> issues = memberAccount.getIssues();
        return issues;
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public List<Reservation> getReservationsByMemberAccount(MemberAccount memberAccount) {
        return memberAccount.getReservations();
    }

    @Override
    public List<Penalty> getPenaltiesByMemberAccount(MemberAccount memberAccount) {
        List<Issue> memberAccountIssues = getIssuesByMemberAccount(memberAccount);
        List<Penalty> memberAccountPenalties = new ArrayList<Penalty>();
        for (Issue issue : memberAccountIssues) {
            Penalty target = issue.getPenalty();
            if (target != null) {
                memberAccountPenalties.add(target);
            }
        }
        return memberAccountPenalties;
    }

    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    private String getHashedPasswordByAccount(Account account) {
        return account.getHashpassword();
    }

    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    private String getSaltByAccount(Account account) {
        return account.getSalt();
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public boolean compare(String inputPassword, String username) throws NoSuchAlgorithmException, IOException {
        Account targetAccount = null;
        try {
            targetAccount = getAccountByUsername(username);
        } catch (UserNotFoundException ignored) {
            // TIME-RESISTANT ATTACK
            // Computation time is equal to the time needed by a legitimate user
        }
        String hash, salt;
        if (targetAccount == null) {
            hash = "";
            salt = "";
        } else {
            hash = getHashedPasswordByAccount(targetAccount);
            salt = getSaltByAccount(targetAccount);
        }
        String hashedInputPassword = encryptionService.encode(inputPassword, salt);
        boolean equal = hashedInputPassword.equals(hash);
        return equal;
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public boolean compare(String inputPassword, Account account) throws NoSuchAlgorithmException, IOException {
        String hash, salt;
        if (account == null) {
            hash = "";
            salt = "";
        } else {
            hash = account.getHashpassword();
            salt = account.getSalt();
        }
        String hashedInputPassword = encryptionService.encode(inputPassword, salt);

        boolean equal = hashedInputPassword.equals(hash);
        return equal;
    }
}
