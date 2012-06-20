/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.perfectlibrary.policy.providers;

import edu.perfectlibrary.policy.LibraryServicePolicyProvider;
import edu.perfectlibrary.policy.OneIssuePerDocumentAllowedRule;
import edu.perfectlibrary.policy.providers.LibraryIssuePolicyProviderLocal;
import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;

/**
 *
 * @author Užívateľ
 */
@Singleton
@Startup
public class LibraryIssuePolicyProvider extends LibraryServicePolicyProvider implements LibraryIssuePolicyProviderLocal {

    @Override
    @PostConstruct
    protected void pupulatePolicies() {
        registerRule(new OneIssuePerDocumentAllowedRule());
    }
}
