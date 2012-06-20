/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.perfectlibrary.business;

import edu.perfectlibrary.model.social.MemberAccount;
import edu.perfectlibrary.model.library.LibraryDocument;
import edu.perfectlibrary.model.library.Reservation;
import javax.ejb.Local;

/**
 *
 * @author Užívateľ
 */
@Local
public interface LibraryReservationServiceLocal {
    
    /**
     * This Methode initialize a process that create the requested reservation.
     */
    public void processReservationRequest(MemberAccount account, LibraryDocument document);
    
    /**
     * This Methode initialize a process that remove the requested reservation.
     */
    public void processReservationRemovingRequest(Reservation reservation);
    
}
