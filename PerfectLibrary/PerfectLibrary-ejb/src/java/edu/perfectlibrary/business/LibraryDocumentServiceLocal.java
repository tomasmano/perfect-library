/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.perfectlibrary.business;

import edu.perfectlibrary.common.NoDocumentWithGivenIdException;
import edu.perfectlibrary.model.social.Author;
import edu.perfectlibrary.model.social.Publisher;
import edu.perfectlibrary.model.library.Book;
import edu.perfectlibrary.model.library.LibraryDocument;
import edu.perfectlibrary.model.library.Review;
import java.util.List;
import javax.ejb.Local;
import javax.persistence.NoResultException;

/**
 *
 * @author Užívateľ
 */
@Local
public interface LibraryDocumentServiceLocal {
    public LibraryDocument getDocumentById(Long id) throws NoDocumentWithGivenIdException;
    public List<LibraryDocument> getDocumentByTitle(String title);
    public List<LibraryDocument> getDocumentByPublisher(Publisher publisher);
    public List<Review> getAllReviewsByLibraryDocument(LibraryDocument document);
    public List<LibraryDocument> getAllLibraryDocuments();
    public List<Author> getAllAuthors();
}
