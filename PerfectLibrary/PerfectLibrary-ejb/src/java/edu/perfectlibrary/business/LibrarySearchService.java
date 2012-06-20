/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.perfectlibrary.business;

import edu.perfectlibrary.model.library.Book;
import edu.perfectlibrary.model.library.LibraryDocument;
//import edu.perfectlibrary.model.library.LibraryDocument_;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author Užívateľ
 */
@Stateless
public class LibrarySearchService implements LibrarySearchServiceLocal {

    @PersistenceContext(unitName = "PerfectLibrary5-ejbPU", type = PersistenceContextType.TRANSACTION)
    EntityManager em;

    @PostConstruct
    public void initPostConstruct() {
        System.out.println(">>>LibrarySearchService EJB: PostConstruct = called OK");
    }

    @PreDestroy
    public void initPreDestroy() {
        System.out.println(">>>LibrarySearchService EJB: PreDestroy = called OK");
    }

    // Criteria API example 
    @Override
    @Deprecated
    public List<Book> findLibraryDocumentByTitle(String clientInput) {
//        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
//        CriteriaQuery<LibraryDocument> cq = criteriaBuilder.createQuery(LibraryDocument.class);
//
//        Root<LibraryDocument> libDocs = cq.from(LibraryDocument.class);
//
//        cq.select(libDocs).where(criteriaBuilder.like(libDocs.get(LibraryDocument_.title), clientInput.trim() + "%"));  // alebo ... +"*"));    ?
//        System.out.println(">>> result type: " + cq.getResultType());
//
//        List<LibraryDocument> searchOutputList = em.createQuery(cq).getResultList();
//        List<Book> books = (List<Book>) (List<?>) searchOutputList; // reference: http://stackoverflow.com/questions/933447/how-do-you-cast-a-list-of-objects-from-one-type-to-another-in-java
//        // alternative #1: 
////        books.addAll(searchOutputList);
//        // alternative #2 (not working !)
////        List<? extends LibraryDocument> searchOutputList = em.createQuery(cq).getResultList();
////        List<Book> books=searchOutputList;
//        return books;
        return null;
    }

    @Override
    public List<LibraryDocument> findLibraryDocumentByTitle2(String clientInput) {
//        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
//        CriteriaQuery<LibraryDocument> cq = criteriaBuilder.createQuery(LibraryDocument.class);
////        Metamodel m = em.getMetamodel();
////        EntityType<LibraryDocument> Book_ = m.entity(LibraryDocument.class);
//        Root<LibraryDocument> libDocs = cq.from(LibraryDocument.class);
////        cq.where(criteriaBuilder.like(libDocs.get(Book_.getDeclaredAttribute("title"), clientInput.trim()+"%")));
////        Predicate predicate=criteriaBuilder.like(criteriaBuilder., clientInput);
////        cq.select(libDocs).where(criteriaBuilder.equal(libDocs.get("title"), clientInput.trim()));
//        cq.select(libDocs).where(criteriaBuilder.like(libDocs.get(LibraryDocument_.title), clientInput.trim() + "%"));  // alebo ... +"*"));    ?
//        System.out.println(">>> result type: " + cq.getResultType());
////        LibraryDocument searchOutput = em.createQuery(cq).getSingleResult();
////        Book test = (Book) searchOutput;
////        System.out.println(">searchOutput: " + test);
////        return test;
//        List<LibraryDocument> searchOutputList = em.createQuery(cq).getResultList();
////        List<Book> books = (List<Book>) (List<?>) searchOutputList; // reference: http://stackoverflow.com/questions/933447/how-do-you-cast-a-list-of-objects-from-one-type-to-another-in-java
//        // alternative #1: 
////        books.addAll(searchOutputList);
//        // alternative #2 (not working !)
////        List<? extends LibraryDocument> searchOutputList = em.createQuery(cq).getResultList();
////        List<Book> books=searchOutputList;
//        return searchOutputList;
        return null;
    }

    //TODO
    @Override
    @Deprecated
    public LibraryDocument findLibraryDocumentByAuthor(String clientInput) {
        return null;
    }

    //TODO
    // Criteria API dynamic search example
    @Override
    public LibraryDocument findLibraryDocument(String clientInput) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<LibraryDocument> c = cb.createQuery(LibraryDocument.class);
        Root<LibraryDocument> doc = c.from(LibraryDocument.class);
        c.select(doc);
        c.distinct(true);
//        Join<>
        return null;
    }

    public void persist(Object object) {
        em.persist(object);
    }
}
