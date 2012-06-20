/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.perfectlibrary.business.confirmation;

/**
 *
 * @author Užívateľ
 */
public interface IssueConfirmationServiceLocal extends ConfirmationService {

    @Override
    void processConfirmationRequest(Object object);
    
}
