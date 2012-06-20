/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.perfectlibrary.model.library;

import java.io.Serializable;
import javax.persistence.*;

/**
 *
 * @author Užívateľ
 */
@Entity
public class Item implements Serializable {

    @OneToOne(mappedBy = "item")
    private Issue issue;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "item_SEQ")
    @SequenceGenerator(name = "item_SEQ", initialValue=100)
    private Long itemId;
    @ManyToOne
    @JoinColumn(name = "libraryDocument_id")
    private LibraryDocument libraryDocument;
    private boolean isAvailable;

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public LibraryDocument getLibraryDocument() {
        return libraryDocument;
    }

    public void setLibraryDocument(LibraryDocument libraryDocument) {
        this.libraryDocument = libraryDocument;
    }

    public Issue getIssue() {
        return issue;
    }

    public void setIssue(Issue issue) {
        this.issue = issue;
    }

    public boolean isIsAvailable() {
        return isAvailable;
    }

    public void setIsAvailable(boolean isAvailable) {
        this.isAvailable = isAvailable;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (itemId != null ? itemId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Item)) {
            return false;
        }
        Item other = (Item) object;
        if ((this.itemId == null && other.itemId != null) || (this.itemId != null && !this.itemId.equals(other.itemId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "edu.simulation.perfectlibrary.model.Item[ id=" + itemId + " ]";
    }
}
