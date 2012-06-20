/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.perfectlibrary.events;

import javax.mail.Message;

/**
 *
 * @author Užívateľ
 */
public interface ConfirmationRequiredEvent {
    public Message createProperConfirmationMessage(javax.mail.Message msg);
}
