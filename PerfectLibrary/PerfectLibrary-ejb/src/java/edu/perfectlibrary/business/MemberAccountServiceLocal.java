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
import edu.perfectlibrary.model.social.MemberAccount;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Užívateľ
 */
@Local
public interface MemberAccountServiceLocal {

    public long getAccountIdByUsername(String username) throws UserNotFoundException;

    public long getAccountIdByFullname(String first, String second) throws UserNotFoundException;

    public MemberAccount findMemberAccoundById(long id);

    public MemberAccount getMemberAccount(long id);

    List<Issue> getMemberAccountIssues(long id);

    List<Reservation> getMemberAccountReservations(long id);

    public java.util.List<Review> getAllReviewsByMember(MemberAccount member);

    public java.util.List<Issue> getAllIssuesByMember(MemberAccount member);

    public List<Reservation> getAllReservationByMember(MemberAccount member);

    public void processInsertReviewRequest(String content, LibraryDocument document, MemberAccount account);
}
