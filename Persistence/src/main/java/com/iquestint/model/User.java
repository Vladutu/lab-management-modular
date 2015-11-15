package com.iquestint.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * This is an model entity that maps the USER table.
 *
 * @author Georgian Vladutu
 */
@Entity
@Table(name = "USER")
@Getter
@Setter
public class User {

    @Id
    @Column(name = "USERNAME")
    private String pnc;

    @Basic
    @Column(name = "PASSWORD")
    private String password;

    @ManyToOne
    @JoinColumn(name = "STATE_ID", referencedColumnName = "ID", nullable = false)
    private UserState userState;

    @ManyToOne
    @JoinColumn(name = "TYPE_ID", referencedColumnName = "ID", nullable = false)
    private UserType userType;

    @OneToOne
    @JoinColumn(name = "USERNAME", referencedColumnName = "PNC", nullable = false)
    private Person person;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof User)) {
            return false;
        }

        User user = (User) o;

        if (pnc != null ? !pnc.equals(user.pnc) : user.pnc != null) {
            return false;
        }
        if (password != null ? !password.equals(user.password) : user.password != null) {
            return false;
        }
        if (userState != null ? !userState.equals(user.userState) : user.userState != null) {
            return false;
        }
        if (userType != null ? !userType.equals(user.userType) : user.userType != null) {
            return false;
        }
        return !(person != null ? !person.equals(user.person) : user.person != null);

    }

    @Override
    public int hashCode() {
        int result = pnc != null ? pnc.hashCode() : 0;
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (userState != null ? userState.hashCode() : 0);
        result = 31 * result + (userType != null ? userType.hashCode() : 0);
        result = 31 * result + (person != null ? person.hashCode() : 0);
        return result;
    }
}
