package com.iquestint.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * This is an model entity that maps the PERSON table.
 *
 * @author Georgian Vladutu
 */
@Entity
@Table(name = "PERSON")
@Inheritance(strategy = InheritanceType.JOINED)
@Getter
@Setter
public class Person {

    @Id
    @Column(name = "PNC", nullable = false)
    @Pattern(regexp = "\\b[1-8]\\d{2}(0[1-9]|1[0-2])(0[1-9]|[12]\\d|3[01])(0[1-9]|[1-4]\\d|5[0-2]|99)\\d{4}\\b")
    private String pnc;

    @Basic
    @Column(name = "FIRST_NAME", nullable = false)
    @Size(min = 3, max = 30)
    private String firstName;

    @Basic
    @Column(name = "LAST_NAME", nullable = false)
    @Size(min = 3, max = 30)
    private String lastName;

    @Basic
    @Column(name = "EMAIL", nullable = false)
    @Pattern(regexp = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$")
    @Size(min = 10, max = 50)
    private String email;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Person)) {
            return false;
        }

        Person person = (Person) o;

        if (pnc != null ? !pnc.equals(person.pnc) : person.pnc != null) {
            return false;
        }
        if (firstName != null ? !firstName.equals(person.firstName) : person.firstName != null) {
            return false;
        }
        if (lastName != null ? !lastName.equals(person.lastName) : person.lastName != null) {
            return false;
        }
        return !(email != null ? !email.equals(person.email) : person.email != null);

    }

    @Override
    public int hashCode() {
        int result = pnc != null ? pnc.hashCode() : 0;
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        return result;
    }
}
