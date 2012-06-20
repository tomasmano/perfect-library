/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.perfectlibrary.model.social;

import edu.perfectlibrary.model.library.Book;
import edu.perfectlibrary.model.library.LibraryDocument;
import java.io.Serializable;
import java.util.List;
import javax.persistence.*;
import javax.xml.bind.annotation.*;

/**
 *
 * @author Užívateľ
 */
@Entity
//@DiscriminatorValue("author")
@XmlRootElement
@XmlAccessorOrder(XmlAccessOrder.ALPHABETICAL)
@XmlAccessorType(XmlAccessType.FIELD)
public class Author implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @XmlTransient
    private Long authorId;
//    
//    @OneToOne(mappedBy="person_id")
//    @PrimaryKeyJoinColumn(name="person_id", referencedColumnName="author_id")
    @OneToOne
    @JoinColumn(name = "person_id")
    @XmlElement
    private Person person;
//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    private Long id;
    @ManyToMany(mappedBy = "authors")
    @XmlTransient
    private List<LibraryDocument> libraryDocuments;

    public Long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public List<LibraryDocument> getLibraryDocuments() {
        return libraryDocuments;
    }

    public void setLibraryDocuments(List<LibraryDocument> libraryDocuments) {
        this.libraryDocuments = libraryDocuments;
    }

    @Override
    public int hashCode() {
        Long temp = authorId;
        int hash = 0;
        hash += (temp != null ? temp.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        return super.equals(object);
    }

    @Override
    public String toString() {
        return "edu.simulation.perfectlibrary.model.Author[ id=" + authorId + " ]";
    }
//
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }

    public Long getId() {
        return authorId;
    }

    public void setId(Long id) {
        this.authorId = id;
    }
}
