/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.perfectlibrary.events;

import edu.perfectlibrary.events.LibraryServiceEvent;
import edu.perfectlibrary.model.library.Issue;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;

/**
 *
 * @author Užívateľ
 */
public class NewIssueEvent extends LibraryServiceEvent implements ConfirmationRequiredEvent{

    public NewIssueEvent(LibraryServiceEventContext libraryServiceEventContext) {
        super(libraryServiceEventContext);
    }
    
    @Override
    public Message createProperConfirmationMessage(javax.mail.Message msg) {
        String emailAddressTarget = eventContext.getAccount().getEmail();
        String name = eventContext.getAccount().getPerson().getFirstname();
        Issue issue = (Issue) eventContext.getLibraryService();
        String documentTitle = issue.getItem().getLibraryDocument().getTitle();
        try {
            msg.setSubject("New Issue Confirmation ('" + documentTitle + "', id: " + issue.getId() + ") | Perfect Library");
            msg.setRecipient(javax.mail.Message.RecipientType.TO,
                    new InternetAddress(emailAddressTarget));
            msg.setText("Dear " + name + ", \n your issue request for document '" + documentTitle + "'(id: " + issue.getId() + ") was succesfully processed. \n Have a nice day. \n Perfect Library Team.");
        } catch (MessagingException e) {
            e.printStackTrace(System.out);
        }
        return msg;
    }
}
