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

@MappedSuperclass
public abstract class Account implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long accountId;
    @OneToOne
    @JoinColumn(name="person_id")
    private Person person;
    String username;
    String hashpassword;
    String salt;
    String phone;
    String email;
    String address;

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getHashpassword() {
        return hashpassword;
    }

    public void setHashpassword(String hashpassword) {
        this.hashpassword = hashpassword;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    @Override
    public int hashCode() {
        Long temp = getAccountId();
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
        return "edu.simulation.perfectlibrary.model.Member[ id=" + getAccountId() + " ]";
    }
}
