/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.perfectlibrary.transfer.utils;

import edu.perfectlibrary.business.LibraryDocumentServiceLocal;
import edu.perfectlibrary.model.social.Author;
import edu.perfectlibrary.model.library.LibraryDocument;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;

/**
 *
 * @author Užívateľ
 */
public class AutoCompleteLibraryDocumentSearchUtil implements Serializable{

    @EJB
    LibraryDocumentServiceLocal libraryDocumentService;
    List<LibraryDocument> libDocsList;
    List<Author> authorsList;
    
    LibraryDocument selectedDocument;

    public AutoCompleteLibraryDocumentSearchUtil() {
    }

    public LibraryDocument getSelectedDocument() {
        return selectedDocument;
    }
    
    public List<Author> getAppropriateAuthors(){

         List<Author> authors = selectedDocument.getAuthors();
        return authors;
    }

    public void setSelectedDocument(LibraryDocument selectedDocument) {
        this.selectedDocument = selectedDocument;
    }
    
    public List<LibraryDocument> completeDocument(String title) {
        libDocsList = libraryDocumentService.getAllLibraryDocuments();
//        authorsList=libraryDocumentService.getAllAuthors();
//        List merge =new ArrayList();
//        merge.addAll(libDocsList);
//        merge.addAll(authorsList);
        List<LibraryDocument> suggestions = new ArrayList<LibraryDocument>();
        String titleToLowerCase=title.toLowerCase();
        for (LibraryDocument libraryDocument : libDocsList) {
            if (libraryDocument.getTitle().toLowerCase().contains(titleToLowerCase)) {
                suggestions.add(libraryDocument);
            }
        }
        return suggestions;
    }
}
