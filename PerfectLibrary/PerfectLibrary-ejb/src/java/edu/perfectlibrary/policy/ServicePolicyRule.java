/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.perfectlibrary.policy;

import edu.perfectlibrary.common.LibraryIssuePolicyViolationException;
import edu.perfectlibrary.model.social.MemberAccount;
import edu.perfectlibrary.model.library.LibraryDocument;

/**
 *
 * @author Užívateľ
 */
public interface ServicePolicyRule {

    public void validatePolicyViolation(MemberAccount account, LibraryDocument document) throws LibraryIssuePolicyViolationException;
}
