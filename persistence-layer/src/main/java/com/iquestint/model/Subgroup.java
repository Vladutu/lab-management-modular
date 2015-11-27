package com.iquestint.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

/**
 * This is an model entity that maps the SUBGROUP table.
 *
 * @author Georgian Vladutu
 */
@Entity
@Getter
@Setter
public class Subgroup {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Basic
    @Column(name = "NAME", unique = true)
    private String name;

    @OneToMany(mappedBy = "subgroup")
    private List<Student> students;

    @OneToMany(mappedBy = "subgroup")
    private List<Laboratory> laboratories;

    public Subgroup() {

    }

    public Subgroup(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Subgroup)) {
            return false;
        }

        Subgroup subgroup = (Subgroup) o;

        if (id != subgroup.id) {
            return false;
        }
        return !(name != null ? !name.equals(subgroup.name) : subgroup.name != null);

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }
}
