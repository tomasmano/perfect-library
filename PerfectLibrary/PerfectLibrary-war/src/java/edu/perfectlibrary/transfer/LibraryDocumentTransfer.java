/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.perfectlibrary.transfer;

import edu.perfectlibrary.business.LibraryDocumentServiceLocal;
import edu.perfectlibrary.business.LibraryIssueServiceLocal;
import edu.perfectlibrary.model.social.Author;
import edu.perfectlibrary.model.library.LibraryDocument;
import edu.perfectlibrary.model.library.Review;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;

/**
 *
 * @author Užívateľ
 */
public class LibraryDocumentTransfer implements Serializable {

    @EJB
    LibraryDocumentServiceLocal libraryDocumentService;
    @EJB
    LibraryIssueServiceLocal libraryIssueService;
    LibraryDocument selectedDocument;
    
    List<Review> reviews;

    public LibraryDocumentTransfer() {
    }

    public LibraryDocument getSelectedDocument() {
        return selectedDocument;
    }

    public List<Author> getAppropriateAuthors() {
        List<Author> authors = selectedDocument.getAuthors();
        return authors;
    }

    public List<Review> getReviews(){
        libraryDocumentService.getAllReviewsByLibraryDocument(selectedDocument);
        return reviews;
    }
    
    public int getAvailabeItemsSize() {
        return libraryIssueService.getAvaliableItemsSize(selectedDocument);
    }

    public void setSelectedDocument(LibraryDocument selectedDocument) {
        this.selectedDocument = selectedDocument;
    }

    // provide suggestions for primefaces autocomplete component
    public List<LibraryDocument> completeDocument(String title) {
        List<LibraryDocument> libDocsList = libraryDocumentService.getAllLibraryDocuments();
        List<LibraryDocument> suggestions = new ArrayList<LibraryDocument>();
        String titleToLowerCase = title.toLowerCase();
        for (LibraryDocument libraryDocument : libDocsList) {
            if (libraryDocument.getTitle().toLowerCase().contains(titleToLowerCase)) {
                suggestions.add(libraryDocument);
            }
        }
        return suggestions;
    }

    public String showDedicatedPage() {
        System.out.println(">>>>>>>>> showDed...");
        String outcome = null;
        outcome = "documentDetails";
//        outcome = "documentDetails.xhtml?faces-redirect=true";
//        outcome = "documentDetails.xhtml";
        return outcome;
    }
}
