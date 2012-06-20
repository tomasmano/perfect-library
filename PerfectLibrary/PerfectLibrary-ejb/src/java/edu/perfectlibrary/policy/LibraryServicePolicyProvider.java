/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.perfectlibrary.policy;

import edu.perfectlibrary.policy.ServicePolicyRule;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import javax.annotation.PostConstruct;
import javax.ejb.Lock;
import javax.ejb.LockType;

/**
 *
 * @author Užívateľ
 */

public abstract class LibraryServicePolicyProvider {

    Set<ServicePolicyRule> rules = new HashSet<ServicePolicyRule>();

    protected abstract void pupulatePolicies();

    final protected void registerRule(ServicePolicyRule... rule) {
        rules.addAll(Arrays.asList(rule));
    }

    @Lock(LockType.READ)
    final public Set<ServicePolicyRule> getPolicyRules() {
        return Collections.unmodifiableSet(rules);
    }
}
