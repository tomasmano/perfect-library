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
public class Issue implements Serializable, LibraryServiceEntity {
    
    @OneToOne(cascade= CascadeType.REMOVE)
    private Penalty penalty;
    
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
//    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "issue_SEQ")
//    @SequenceGenerator(name = "issue_SEQ", initialValue=100, allocationSize=200)
    private Long id;
    
    @ManyToOne
    private MemberAccount memberAccount;
    @OneToOne
    private Item item;
    
    @Temporal(TemporalType.DATE)
    private Date since;
    
    @Temporal(TemporalType.DATE)
    private Date until;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public MemberAccount getMemberAccount() {
        return memberAccount;
    }

    public void setMemberAccount(MemberAccount memberAccount) {
        this.memberAccount = memberAccount;
    }

    public Penalty getPenalty() {
        return penalty;
    }

    public void setPenalty(Penalty penalty) {
        this.penalty = penalty;
    }

    public Date getSince() {
        return since;
    }

    public void setSince(Date since) {
        this.since = since;
    }

    public Date getUntil() {
        return until;
    }

    public void setUntil(Date until) {
        this.until = until;
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
        if (!(object instanceof Issue)) {
            return false;
        }
        Issue other = (Issue) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "edu.simulation.perfectlibrary.model.library.Issue[ id=" + id + " ]";
    }
    
}
