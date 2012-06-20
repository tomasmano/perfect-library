/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.perfectlibrary.model.library;

import edu.perfectlibrary.model.social.MemberAccount;
import edu.perfectlibrary.model.social.Account;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

/**
 *
 * @author Užívateľ
 */
@Entity
public class Reservation implements Serializable, LibraryServiceEntity {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @OneToOne
    private MemberAccount memberAccount;
    
    @ManyToOne
    @JoinColumn(name="reserved_lib_doc")
    private LibraryDocument libraryDocument;
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date since;

    public LibraryDocument getLibraryDocument() {
        return libraryDocument;
    }

    public void setLibraryDocument(LibraryDocument libraryDocument) {
        this.libraryDocument = libraryDocument;
    }

    public MemberAccount getMemberAccount() {
        return memberAccount;
    }

    public void setMemberAccount(MemberAccount memberAccount) {
        this.memberAccount = memberAccount;
    }

    public Date getSince() {
        return since;
    }

    public void setSince(Date since) {
        this.since = since;
    }

    public MemberAccount getAccount() {
        return memberAccount;
    }

    public void setAccount(MemberAccount memberAccount) {
        this.memberAccount = memberAccount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
        if (!(object instanceof Reservation)) {
            return false;
        }
        Reservation other = (Reservation) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "edu.simulation.perfectlibrary.model.library.Reservation[ id=" + id + " ]";
    }
}
