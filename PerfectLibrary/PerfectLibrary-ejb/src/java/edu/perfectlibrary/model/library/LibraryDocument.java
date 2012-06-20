/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.perfectlibrary.model.library;

import edu.perfectlibrary.model.social.Author;
import edu.perfectlibrary.model.social.Publisher;
import edu.perfectlibrary.enums.Specification;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/**
 *
 * @author Užívateľ
 */
@Entity
@DiscriminatorColumn(name = "doc_type", discriminatorType = DiscriminatorType.STRING)
@Inheritance(strategy = InheritanceType.JOINED)
@XmlRootElement
@XmlAccessorOrder(XmlAccessOrder.ALPHABETICAL)
@XmlAccessorType(XmlAccessType.FIELD)
public abstract class LibraryDocument implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @XmlTransient
    private Long docId;
    @XmlElement
    private String title;
//    @ManyToMany(fetch= FetchType.EAGER)  //  ? ? ?    
    @ManyToMany
//    @JoinTable(name = "librarydocument_author",
//    joinColumns = {
//        @JoinColumn(name = "lib_doc_id", referencedColumnName = "lib_doc_id")},
//    inverseJoinColumns = {
//        @JoinColumn(name = "auth_id", referencedColumnName = "auth_id")})
//    @XmlIDREF
    @XmlElement
    private List<Author> authors;

    @OneToMany(cascade = {CascadeType.REMOVE, CascadeType.MERGE}, mappedBy = "libraryDocument")
    @MapKey(name = "itemId")
    @XmlTransient
    private Map<Integer, Item> items;   // construct source: "Listing 5-11, chapter 5, page 119, PRO JPA: Mastering the Java Persistance API 2009", before: private List<Item> items;
    @ManyToOne
    @JoinColumn(name = "id_publisher")
    private Publisher publisher;
    @OneToMany(cascade = {CascadeType.REMOVE, CascadeType.MERGE}, mappedBy = "libraryDocument")
    @XmlTransient
    private List<Review> reviews;
    @OneToMany(cascade = {CascadeType.REMOVE, CascadeType.MERGE}, mappedBy = "libraryDocument")
    @OrderBy("since ASC")
    @XmlTransient
    private List<Reservation> reservations;
    @OneToMany       // unidirectional
//    @JoinTable(name = "lib_doc_genre", // optional
//    joinColumns =
//    @JoinColumn(name = "lib_doc_id"),
//    inverseJoinColumns =
//    @JoinColumn(name = "genre_id"))
    @XmlTransient
    private Set<Genre> genres;
    @Transient
    @XmlTransient
    private Map<Specification, Object> specifications = new HashMap<Specification, Object>();

    public Long getDocId() {
        return docId;
    }

    public void setDocId(Long docId) {
        this.docId = docId;
    }

    public Set<Genre> getGenres() {
        return genres;
    }

    public void setGenres(Set<Genre> genres) {
        this.genres = genres;
    }

    public List<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(List<Reservation> reservations) {
        this.reservations = reservations;
    }

//    @ElementCollection
//    @CollectionTable(name="lib_genres")
//    @Column(name="genres")
//    Set<GenreType> genres;
    public List<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }

    public Map<Integer, Item> getItems() {
        return items;
    }

    public void setItems(Map<Integer, Item> items) {
        this.items = items;
    }

    public Publisher getPublisher() {
        return publisher;
    }

    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Map<Specification, Object> getSpecifications() {
        return specifications;
    }

    public void setSpecifications(Map<Specification, Object> specifications) {
        this.specifications = specifications;
    }

    public List<Specification> getSpecificationsKeys() {
        List<Specification> keys = new ArrayList<Specification>(getSpecifications().keySet());
//        for (Iterator i = getSpecifications().keySet().iterator(); i.hasNext();) {
//            Specification specificationName = (Specification) i.next();
//            keys.add(specificationName);
//        }
        return keys;
//        return keys;
    }

    public List<Object> getSpecificationValues() {
        List<Object> values = new ArrayList<Object>();
        Map specs = getSpecifications();
        for (Map.Entry entry : (Set<Entry>) specs.entrySet()) {
            values.add(entry.getValue());
        }
        return values;
    }

    protected abstract void populateSpecifications();

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (docId != null ? docId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof LibraryDocument)) {
            return false;
        }
        LibraryDocument other = (LibraryDocument) object;
        if ((this.docId == null && other.docId != null) || (this.docId != null && !this.docId.equals(other.docId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "edu.simulation.perfectlibrary.model.library.LibraryDocument[ id=" + docId + " ]";
    }
}
