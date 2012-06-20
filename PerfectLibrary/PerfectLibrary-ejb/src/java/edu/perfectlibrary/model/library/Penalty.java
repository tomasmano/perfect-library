/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.perfectlibrary.model.library;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

/**
 *
 * @author Užívateľ
 */
@Entity
public class Penalty implements Serializable, LibraryServiceEntity {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @OneToOne(mappedBy = "penalty", cascade = CascadeType.MERGE)
    private Issue issue;
    @Temporal(TemporalType.DATE)
    private Date since;

    public Issue getIssue() {
        return issue;
    }

    public void setIssue(Issue issue) {
        this.issue = issue;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getSince() {
        return since;
    }

    public void setSince(Date since) {
        this.since = since;
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
        if (!(object instanceof Penalty)) {
            return false;
        }
        Penalty other = (Penalty) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "edu.simulation.perfectlibrary.model.library.Penalty[ id=" + id + " ]";
    }
}
