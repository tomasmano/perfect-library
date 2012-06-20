/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.perfectlibrary.model.social;

import edu.perfectlibrary.model.library.Issue;
import edu.perfectlibrary.model.library.Reservation;
import java.io.Serializable;
import java.util.List;
import javax.persistence.*;

/**
 *
 * @author Užívateľ
 */
@Entity
public class MemberAccount extends Account implements Serializable {

    @OneToMany(mappedBy = "memberAccount", cascade= CascadeType.MERGE)
    private List<Issue> issues;
    @OneToMany(mappedBy = "memberAccount", cascade = CascadeType.ALL)
    private List<Reservation> reservations;

    public List<Issue> getIssues() {
        return issues;
    }

    public void setIssues(List<Issue> issues) {
        this.issues = issues;
    }

    public List<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(List<Reservation> reservations) {
        this.reservations = reservations;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (this.getAccountId() != null ? this.getAccountId().hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MemberAccount)) {
            return false;
        }
        MemberAccount other = (MemberAccount) object;
        if ((this.getAccountId() == null && other.getAccountId() != null) || (this.getAccountId() != null && !this.getAccountId().equals(other.getAccountId()))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "edu.simulation.perfectlibrary.model.human.Account[ id=" + getAccountId() + " ]";
    }
}
