package com.iquestint.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * @author vladu
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

    @Basic
    @Column(name = "EMAIL")
    private String email;

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
        if (!super.equals(o)) {
            return false;
        }

        User user = (User) o;

        if (password != null ? !password.equals(user.password) : user.password != null) {
            return false;
        }
        if (email != null ? !email.equals(user.email) : user.email != null) {
            return false;
        }
        if (userState != null ? !userState.equals(user.userState) : user.userState != null) {
            return false;
        }
        return !(userType != null ? !userType.equals(user.userType) : user.userType != null);

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (userState != null ? userState.hashCode() : 0);
        result = 31 * result + (userType != null ? userType.hashCode() : 0);
        return result;
    }
}
