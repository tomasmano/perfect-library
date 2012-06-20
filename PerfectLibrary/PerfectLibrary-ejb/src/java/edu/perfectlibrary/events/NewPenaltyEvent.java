/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.perfectlibrary.events;

import edu.perfectlibrary.model.library.Item;
import edu.perfectlibrary.model.library.Penalty;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;

/**
 *
 * @author Užívateľ
 */
public class NewPenaltyEvent extends LibraryServiceEvent implements ConfirmationRequiredEvent {

    public NewPenaltyEvent(LibraryServiceEventContext libraryServiceEventContext) {
        super(libraryServiceEventContext);
    }
    
    @Override
    public Message createProperConfirmationMessage(Message msg) {
        String emailAddressTarget = eventContext.getAccount().getEmail();
        String name = eventContext.getAccount().getPerson().getFirstname();
        Penalty penalty = (Penalty) eventContext.getLibraryService();
        Item expiredIssueItem = penalty.getIssue().getItem();
        String documentTitle = expiredIssueItem.getLibraryDocument().getTitle();
        try {
            msg.setSubject("New Penalty Confirmation ('" + documentTitle + "', id: " + penalty.getId() + ") | Perfect Library");
            msg.setRecipient(javax.mail.Message.RecipientType.TO,
                    new InternetAddress(emailAddressTarget));
            msg.setText("Dear " + name + ", \nperiod for the document '" + documentTitle + "'(id: " + expiredIssueItem.getItemId()+ ") has just expired. New penalty for your account had to be registered. \nHave a nice day. \nPerfect Library Team.");
        } catch (MessagingException e) {
            e.printStackTrace(System.out);
        }
        return msg;
    }
}
