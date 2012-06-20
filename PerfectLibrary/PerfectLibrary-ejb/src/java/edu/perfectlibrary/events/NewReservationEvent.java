/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.perfectlibrary.events;

import edu.perfectlibrary.events.LibraryServiceEvent;
import edu.perfectlibrary.model.library.Issue;
import edu.perfectlibrary.model.library.Reservation;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;

/**
 *
 * @author Užívateľ
 */
public class NewReservationEvent extends LibraryServiceEvent implements ConfirmationRequiredEvent{

    public NewReservationEvent(LibraryServiceEventContext libraryServiceEventContext) {
        super(libraryServiceEventContext);
    }
    
    @Override
    public Message createProperConfirmationMessage(javax.mail.Message msg) {
        String emailAddressTarget = eventContext.getAccount().getEmail();
        String name = eventContext.getAccount().getPerson().getFirstname();
        Reservation reservation = (Reservation) eventContext.getLibraryService();
        String documentTitle = reservation.getLibraryDocument().getTitle();
        try {
            msg.setSubject("New Reservation Confirmation ('" + documentTitle + "', id: " + reservation.getId() + ") | Perfect Library");
            msg.setRecipient(javax.mail.Message.RecipientType.TO,
                    new InternetAddress(emailAddressTarget));
            msg.setText("Dear " + name + ", \n your reservation request for document '" + documentTitle + "'(id: " + reservation.getId() + ") was succesfully processed. \n Have a nice day. \n Perfect Library Team.");
        } catch (MessagingException e) {
            e.printStackTrace(System.out);
        }
        return msg;
    }
}
