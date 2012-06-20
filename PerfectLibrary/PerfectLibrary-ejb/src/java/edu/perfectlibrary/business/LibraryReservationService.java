/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.perfectlibrary.business;

import edu.perfectlibrary.events.LibraryServiceEventContext;
import edu.perfectlibrary.business.confirmation.ReservationConfirmationServiceLocal;
import edu.perfectlibrary.events.LibraryServiceEvent;
import edu.perfectlibrary.events.NewReservationEvent;
import edu.perfectlibrary.model.social.MemberAccount;
import edu.perfectlibrary.model.library.LibraryDocument;
import edu.perfectlibrary.model.library.Reservation;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

/**
 *
 * @author Užívateľ
 */
@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class LibraryReservationService implements LibraryReservationServiceLocal {

    @PersistenceContext(unitName = "PerfectLibrary5-ejbPU", type = PersistenceContextType.TRANSACTION)
    EntityManager em;
    @EJB
    ReservationConfirmationServiceLocal reservationConfirmationService;

    private Reservation createReservation(MemberAccount account, LibraryDocument document) {
        Reservation reservation = new Reservation();
        reservation.setAccount(account);
        reservation.setLibraryDocument(document);
        reservation.setSince(new Date());
        em.persist(reservation);
        return reservation;
    }
    
    public List<Reservation> getAllReservationByLibraryDocument(LibraryDocument document){
         return (List<Reservation>) em.createQuery("SELECT r FROM Reservation WHERE r.libraryDocument=:document").setParameter("document", document).getResultList();
    }

    /**
     * This Methode initialize a process that create the requested reservation.
     */
    @Override
    public void processReservationRequest(MemberAccount account, LibraryDocument document) {
        Reservation reservation = createReservation(account, document);
        newReservationCreatedEvent(account, reservation);
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    private void newReservationCreatedEvent(final MemberAccount account, final Reservation reservation) {
        LibraryServiceEventContext context = new LibraryServiceEventContext(account, reservation);
        LibraryServiceEvent event = new NewReservationEvent(context);
        reservationConfirmationService.processConfirmationRequest(event);
    }

    /**
     * This Methode initialize a process that remove the requested reservation.
     */
    @Override
    public void processReservationRemovingRequest(Reservation reservation) {
        removeReservation(reservation);
    }

    private void removeReservation(Reservation reservation) {
        Reservation removed = em.merge(reservation);
        em.remove(removed);
    }
}
