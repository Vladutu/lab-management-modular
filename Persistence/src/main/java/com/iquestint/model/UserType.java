package com.iquestint.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

/**
 * @author vladu
 */
@Entity
@Table(name = "TYPE", uniqueConstraints = {
    @UniqueConstraint(columnNames = "name") })
@Getter
@Setter
public class UserType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;

    @Basic
    @Column(name = "NAME", unique = true)
    private String name;

    @OneToMany(mappedBy = "userType")
    private List<User> users;

    public UserType() {
    }

    public UserType(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof UserType)) {
            return false;
        }

        UserType userType = (UserType) o;

        if (id != userType.id) {
            return false;
        }
        return !(name != null ? !name.equals(userType.name) : userType.name != null);

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }
}
