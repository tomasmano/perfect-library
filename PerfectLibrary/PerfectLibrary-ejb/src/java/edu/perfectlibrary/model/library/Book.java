/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.perfectlibrary.model.library;

import edu.perfectlibrary.common.SpecificationsProcessingStrategy;
import edu.perfectlibrary.common.SpecificationsProcessingStrategyValue;
import edu.perfectlibrary.common.SpecifiedLibraryDocumentProperty;
import edu.perfectlibrary.enums.BookSpecification;
import edu.perfectlibrary.enums.BookCoverType;
import edu.perfectlibrary.enums.LibraryReadingDocumentSpecification;
import edu.perfectlibrary.enums.Specification;
import java.io.Serializable;
import java.util.Map;
import javax.persistence.*;
import javax.xml.bind.annotation.*;

/**
 *
 * @author Užívateľ
 */
@Entity
@DiscriminatorValue(value = "book")
@SpecificationsProcessingStrategy(SpecificationsProcessingStrategyValue.REFLEXION)
//@NamedQuery(name = Book.Q_GET_ALL_BOOKS, query = "SELECT b FROM Book b"))
@XmlRootElement
@XmlAccessorOrder(XmlAccessOrder.ALPHABETICAL)
@XmlAccessorType(XmlAccessType.FIELD) 
public class Book extends LibraryReadingDocument implements Serializable {
//
//    @Transient
//    public static final String Q_GET_ALL_BOOKS = "Book.GET_ALL";

    private static final long serialVersionUID = 1L;
    @SpecifiedLibraryDocumentProperty
    @Enumerated(EnumType.STRING)
    @XmlElement
    private BookCoverType bookCoverType;

    public BookCoverType getBookCoverType() {
        return bookCoverType;
    }

    public void setBookCoverType(BookCoverType bookCoverType) {
        this.bookCoverType = bookCoverType;
    }
    
    @Override
    protected void populateSpecifications(){
        Map<Specification, Object> specifications = super.getSpecifications();
        specifications.put(LibraryReadingDocumentSpecification.LANGUAGE, getLanguage());
        specifications.put(LibraryReadingDocumentSpecification.PAGES, getPages());
        specifications.put(BookSpecification.COVER, getBookCoverType());
    }
    
    @Override
    public Map<Specification, Object> getSpecifications(){
        this.populateSpecifications();
        return super.getSpecifications();
    }

    @Override
    public int hashCode() {
        Long temp = getDocId();
        int hash = 0;
        hash += (temp != null ? temp.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        return super.equals(object);
    }

    @Override
    public String toString() {
        return "edu.simulation.perfectlibrary.model.Book[ id=" + getDocId() + " ]";
    }
}
