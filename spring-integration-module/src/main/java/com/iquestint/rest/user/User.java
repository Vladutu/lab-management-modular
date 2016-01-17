package com.iquestint.rest.user;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 * This class represents a user.
 *
 * @author Georgian Vladutu
 */
@XmlRootElement(name = "User")
@XmlAccessorType(XmlAccessType.FIELD)
public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    @XmlElement(name = "ID")
    private String id;

    @XmlElement(name = "CNP")
    private String cnp;

    @XmlElement(name = "FirstName")
    private String firstName;

    @XmlElement(name = "LastName")
    private String lastName;

    @XmlElement(name = "Pass")
    private String pass;

    public User(String string, String cnp, String firstName, String lastName, String pass) {
        this.id = string;
        this.cnp = cnp;
        this.firstName = firstName;
        this.lastName = lastName;
        this.pass = pass;
    }

    public User() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCnp() {
        return cnp;
    }

    public void setCnp(String cnp) {
        this.cnp = cnp;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    @Override
    public String toString() {
        return "<User>" + "<ID>" + id + "</ID>" + "<CNP>" + cnp + "</CNP>" + "<FirstName>" + firstName + "</FirstName>"
            + "<LastName>" + lastName + "</LastName>" + "<Pass>" + pass + "</Pass>" + "</User>";
    }
}
