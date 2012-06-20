/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.perfectlibrary.business.confirmation;

import edu.perfectlibrary.events.LibraryServiceEvent;
import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.jms.*;

/**
 *
 * @author Užívateľ
 */
@Stateless
public class PenaltyConfirmationService implements PenaltyConfirmationServiceLocal {

    @Resource(mappedName = "jms/PerfectLibraryConfirmationQueue")
    private QueueConnectionFactory queueFactory;
    @Resource(mappedName = "confirmationRequestsQueue")
    private Queue queue;
    private LibraryServiceEvent newPenaltyEvent;

    @Override
    public void processConfirmationRequest(Object object) {
        if (object != null) {
            this.newPenaltyEvent = (LibraryServiceEvent) object;
            pushConfirmationRequest();
        }
    }

    private void pushConfirmationRequest() {
        Connection queueConnection = null;
        javax.jms.Session session = null;
        MessageProducer producer = null;
        Message message = null;
        try {

            queueConnection = queueFactory.createConnection();
            session = queueConnection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            producer = session.createProducer(queue);

            message = session.createObjectMessage(newPenaltyEvent);
            producer.send(message);

        } catch (JMSException e) {
            e.printStackTrace(System.out);
        }
    }
}
