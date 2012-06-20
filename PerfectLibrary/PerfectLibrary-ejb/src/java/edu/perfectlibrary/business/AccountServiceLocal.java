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
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Užívateľ
 */
@Local
public interface AccountServiceLocal {

    public Account getAccount(long id);

    public Account getAccountByUsername(String clientInput) throws UserNotFoundException;

    public List<Issue> getIssuesByMemberAccount(MemberAccount memberAccount);

    public List<Reservation> getReservationsByMemberAccount(MemberAccount memberAccount);

    public List<Penalty> getPenaltiesByMemberAccount(MemberAccount memberAccount);

    public boolean compare(String inputPassword, String username) throws NoSuchAlgorithmException, IOException;

    public boolean compare(String inputPassword, Account account) throws NoSuchAlgorithmException, IOException;
}
