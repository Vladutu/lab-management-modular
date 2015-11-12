package com.iquestint.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

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
    @Column(name = "PNC")
    private String pnc;

    @Basic
    @Column(name = "FIRST_NAME")
    private String firstName;

    @Basic
    @Column(name = "LAST_NAME")
    private String lastName;

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
        return !(firstName != null ? !firstName.equals(person.firstName) : person.firstName != null) &&
            !(lastName != null ? !lastName.equals(person.lastName) : person.lastName != null);

    }

    @Override
    public int hashCode() {
        int result = pnc != null ? pnc.hashCode() : 0;
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        return result;
    }
}
