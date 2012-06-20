/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.perfectlibrary.events;

import java.io.Serializable;
import javax.mail.Message;

/**
 *
 * @author Užívateľ
 */
public abstract class LibraryServiceEvent implements Serializable{

    final LibraryServiceEventContext eventContext;

    public LibraryServiceEvent(LibraryServiceEventContext libraryServiceEventContext) {
        this.eventContext=libraryServiceEventContext;
    }
}
