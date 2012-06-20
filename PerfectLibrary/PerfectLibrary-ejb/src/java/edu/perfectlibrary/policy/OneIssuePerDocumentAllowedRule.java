/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.perfectlibrary.policy;

import edu.perfectlibrary.common.OneIssuePerDocumentAllowedViolationException;
import edu.perfectlibrary.model.social.MemberAccount;
import edu.perfectlibrary.model.library.Issue;
import edu.perfectlibrary.model.library.LibraryDocument;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.ejb.EJB;

/**
 *
 * @author Užívateľ
 */
public class OneIssuePerDocumentAllowedRule implements ServicePolicyRule {
    
    private Set<LibraryDocument> getIssuedDocumentsByIssues(List<Issue> issues) {
        Set<LibraryDocument> issuedDocuments = new HashSet<LibraryDocument>();
        for (Issue issue : issues) {
            issuedDocuments.add(issue.getItem().getLibraryDocument());
        }
        return issuedDocuments;
    }

    private boolean checkOneIssuePerDocumentRuleVioaltion(MemberAccount account, LibraryDocument document) {
        List<Issue> accountIssues = account.getIssues();
        Set<LibraryDocument> isssuedDocuments = getIssuedDocumentsByIssues(accountIssues);
        return isssuedDocuments.contains(document);
    }

    @Override
    public void validatePolicyViolation(MemberAccount account, LibraryDocument document) throws OneIssuePerDocumentAllowedViolationException{
        boolean issuePolicyViolated;
        issuePolicyViolated = checkOneIssuePerDocumentRuleVioaltion(account, document);
        if (issuePolicyViolated) {
            throw new OneIssuePerDocumentAllowedViolationException();
        }
    }
}
