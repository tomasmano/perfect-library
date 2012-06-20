/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.perfectlibrary.events;

import edu.perfectlibrary.model.social.Account;
import edu.perfectlibrary.model.social.MemberAccount;
import edu.perfectlibrary.model.library.LibraryServiceEntity;
import java.io.Serializable;

/**
 *
 * @author Užívateľ
 */
public class LibraryServiceEventContext implements Serializable {

    private final Account account;
    private final LibraryServiceEntity libraryService;

    /**
     * @param account account is subject of the event providing the event part context 
     * @param libraryService  object implementing LibraryServiceEntity interface (all entities representing library 
     * service (e. g. Issue, Reservation etc) implements this interface) is another subject of the 
     * event providing providing event context
     */
    public LibraryServiceEventContext(Account account, LibraryServiceEntity libraryService) {
        this.account = account;
        this.libraryService = libraryService;
    }

    public Account getAccount() {
        return account;
    }

    public LibraryServiceEntity getLibraryService() {
        return libraryService;
    }
}
