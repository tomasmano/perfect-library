/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.perfectlibrary.model.library;

import edu.perfectlibrary.model.social.Account;
import edu.perfectlibrary.model.social.MemberAccount;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;
import javax.xml.bind.annotation.*;

/**
 *
 * @author Užívateľ
 */
@Entity
@XmlRootElement
@XmlAccessorOrder(XmlAccessOrder.ALPHABETICAL)
@XmlAccessorType(XmlAccessType.FIELD)
public class Review implements Serializable, LibraryServiceEntity {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @XmlTransient
    private Long id;
    
    @OneToOne
    @XmlTransient
    private MemberAccount memberAccount;
    
    @ManyToOne
    @JoinColumn(name="reviewed_lib_doc")
    @XmlTransient
    private LibraryDocument libraryDocument;
    
    @Lob
//    @Basic(fetch= FetchType.LAZY)
    @Basic
    @XmlElement
    private String content;
    
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    @XmlElement
    private Date created;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public MemberAccount getMemberAccount() {
        return memberAccount;
    }

    public void setMemberAccount(MemberAccount memberAccount) {
        this.memberAccount = memberAccount;
    }
    
    public LibraryDocument getLibraryDocument() {
        return libraryDocument;
    }

    public void setLibraryDocument(LibraryDocument libraryDocument) {
        this.libraryDocument = libraryDocument;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Review)) {
            return false;
        }
        Review other = (Review) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "edu.simulation.perfectlibrary.model.library.Review[ id=" + id + " ]";
    }
    
}
