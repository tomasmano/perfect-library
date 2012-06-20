/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.perfectlibrary.business;

import edu.perfectlibrary.common.NoDocumentWithGivenIdException;
import edu.perfectlibrary.model.social.Author;
import edu.perfectlibrary.model.social.Publisher;
import edu.perfectlibrary.model.library.LibraryDocument;
import edu.perfectlibrary.model.library.Review;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

/**
 *
 * @author Užívateľ
 */
@Stateless
public class LibraryDocumentService implements LibraryDocumentServiceLocal {

    @PersistenceContext(unitName = "PerfectLibrary5-ejbPU", type = PersistenceContextType.TRANSACTION)
    EntityManager em;

    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public LibraryDocument getDocumentById(Long id) throws NoDocumentWithGivenIdException {
        LibraryDocument document = null;
        try {
            document = (LibraryDocument) em.createQuery("SELECT d FROM LibraryDocument d WHERE d.docId=:id").setParameter("id", id).getSingleResult();
        } catch (NoResultException e) {
            throw new NoDocumentWithGivenIdException();
        }
        return document;
    }

    @Override
    public List<LibraryDocument> getDocumentByTitle(String title) {
        return (List<LibraryDocument>) em.createQuery("SELECT d FROM LibraryDocument d WHERE d.title=:title").setParameter("title", title).getResultList();
    }

    @Override
    public List<LibraryDocument> getDocumentByPublisher(Publisher publisher) {
        return (List<LibraryDocument>) em.createQuery("SELECT d FROM LibraryDocument d WHERE d.publisher=:publisher").setParameter("publisher", publisher).getResultList();
    }

    @Override
    public List<Review> getAllReviewsByLibraryDocument(LibraryDocument document) {
        return (List<Review>) em.createQuery("SELECT r FROM Review r WHERE r.libraryDocument=:document").setParameter("document", document).getResultList();
    }

    @Override
    public List<LibraryDocument> getAllLibraryDocuments() {
        List<LibraryDocument> documents = em.createQuery("SELECT d FROM LibraryDocument d").getResultList();
        return documents;
    }

    @Override
    public List<Author> getAllAuthors() {
        List<Author> authors = em.createQuery("SELECT a FROM Author a").getResultList();
        return authors;
    }
}
