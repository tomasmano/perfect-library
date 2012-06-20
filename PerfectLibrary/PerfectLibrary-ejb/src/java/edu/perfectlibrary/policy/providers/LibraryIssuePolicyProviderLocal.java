/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.perfectlibrary.policy.providers;

import edu.perfectlibrary.policy.ServicePolicyRule;
import java.util.Set;
import javax.ejb.Local;

/**
 *
 * @author Užívateľ
 */
@Local
public interface LibraryIssuePolicyProviderLocal {
    public Set<ServicePolicyRule> getPolicyRules();
}
