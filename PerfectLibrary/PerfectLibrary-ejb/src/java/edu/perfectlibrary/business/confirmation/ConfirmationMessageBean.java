/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.perfectlibrary.business.confirmation;

import edu.perfectlibrary.events.ConfirmationRequiredEvent;
import edu.perfectlibrary.events.LibraryServiceEvent;
import javax.annotation.Resource;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.*;
import javax.mail.MessagingException;
import javax.mail.Transport;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author Užívateľ
 */
@MessageDriven(mappedName = "confirmationRequestsQueue", activationConfig = {
    @ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge"),
    @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue")
})
public class ConfirmationMessageBean implements MessageListener {

    @Resource(name = "mail/edu.perfectlibrary")
    private javax.mail.Session mailSession;

    public ConfirmationMessageBean() {
    }

    @Override
    public void onMessage(Message message) {
        System.out.println(">>>>>> DEBUG: "+message);
        javax.jms.Message msg = null;
        ObjectMessage om = null;
        try {
//            msg = (ObjectMessage) message;
            om=(ObjectMessage)message;
            ConfirmationRequiredEvent event = (ConfirmationRequiredEvent)om.getObject();
            sendConfirmationEmail(event);
        } catch (JMSException e) {
            e.printStackTrace(System.out);
        }
    }

    private void sendConfirmationEmail(ConfirmationRequiredEvent event) {
        javax.mail.Message msg = new MimeMessage(mailSession);
        try {
            
            msg = event.createProperConfirmationMessage(msg);

            Transport.send(msg);
        } catch (MessagingException me) {
            me.printStackTrace(System.out);
        }
    }
}