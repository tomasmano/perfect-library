/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.perfectlibrary.business.confirmation;

import javax.ejb.Local;

/**
 *
 * @author Užívateľ
 */
@Local
public interface ConfirmationService {
     
    public void processConfirmationRequest(Object object);
    
}
