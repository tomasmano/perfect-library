/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.perfectlibrary.business;

import edu.perfectlibrary.common.LibraryIssuePolicyViolationException;
import edu.perfectlibrary.common.NoItemsAvailableException;
import edu.perfectlibrary.common.OneIssuePerDocumentAllowedViolationException;
import edu.perfectlibrary.model.social.MemberAccount;
import edu.perfectlibrary.model.library.Issue;
import edu.perfectlibrary.model.library.LibraryDocument;
import java.util.Date;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Užívateľ
 */
@Local
public interface LibraryIssueServiceLocal {

    public Issue getIssueById(long id);

    public List<Issue> getIssuesByMemberAccount(MemberAccount memberAccount);

    public void updateIssue(Issue issue);
    
    public List<Issue> getExpiredIssues();

    public void processIssueRequest(MemberAccount member, LibraryDocument document) throws NoItemsAvailableException, LibraryIssuePolicyViolationException;

    public void proccesExpiredIssue(Issue expired);

    public void processIssueCancelation(Issue issue);
    
    public void handleExpiredIssuesRoutine();

    public int getAvaliableItemsSize(LibraryDocument document);

}