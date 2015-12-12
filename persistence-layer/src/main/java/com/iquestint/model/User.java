package com.iquestint.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

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
    @Column(name = "USERNAME", nullable = false)
    @Pattern(regexp = "\\b[1-8]\\d{2}(0[1-9]|1[0-2])(0[1-9]|[12]\\d|3[01])(0[1-9]|[1-4]\\d|5[0-2]|99)\\d{4}\\b")
    private String pnc;

    @Basic
    @Column(name = "PASSWORD", nullable = false)
    @Size(min = 6)
    private String password;

    @ManyToOne
    @JoinColumn(name = "STATE_ID", referencedColumnName = "ID", nullable = false)
    private UserState userState;

    @ManyToOne
    @JoinColumn(name = "TYPE_ID", referencedColumnName = "ID", nullable = false)
    private UserType userType;

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
        return !(userType != null ? !userType.equals(user.userType) : user.userType != null);

    }

    @Override
    public int hashCode() {
        int result = pnc != null ? pnc.hashCode() : 0;
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (userState != null ? userState.hashCode() : 0);
        result = 31 * result + (userType != null ? userType.hashCode() : 0);
        return result;
    }
}
