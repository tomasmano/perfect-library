/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.perfectlibrary.transfer.utils;

import java.io.Serializable;

/**
 *
 * @author Užívateľ
 */
public class NavigationUtil implements Serializable {

    public String goToReservations() {
        return "reservations";
    }
    public String goToIssues() {
        return "issues";
    }
    public String goToAlerts() {
        return "alerts";
    }
    public String goToReviews() {
        return "reviews";
    }
    public String goToSignIn() {
        return "signin";
    }
    public String goToAccount(){
        return "backToAccount";
    }
    public String goToRegistration() {
        return "registration";
    }
    public String goToWriteReview() {
        return "writeReview";
    }
    public String goToDocumentDetails() {
        return "documentDetails";
    }
}
