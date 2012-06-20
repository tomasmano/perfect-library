/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.perfectlibrary.business;

import edu.perfectlibrary.model.library.Book;
import edu.perfectlibrary.model.library.LibraryDocument;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Užívateľ
 */
@Local
public interface LibrarySearchServiceLocal {

    LibraryDocument findLibraryDocument(String clientInput);

    LibraryDocument findLibraryDocumentByAuthor(String clientInput);

    List<Book> findLibraryDocumentByTitle(String clientInput);

    public List<LibraryDocument> findLibraryDocumentByTitle2(String clientInput);
    
}
