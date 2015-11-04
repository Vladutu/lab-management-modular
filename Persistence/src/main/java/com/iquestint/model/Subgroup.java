package com.iquestint.model;

import javax.persistence.*;
import java.util.List;

/**
 * @author vladu
 */
@Entity
public class Subgroup {

    @Id
    @Column(name = "SGP_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Basic
    @Column(name = "NAME", unique = true)
    private String name;

    @OneToMany(mappedBy = "subgroup")
    private List<Student> students;

    public Subgroup() {

    }

    public Subgroup(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Subgroup subgroup = (Subgroup) o;

        if (id != subgroup.id) {
            return false;
        }
        if (name != null ? !name.equals(subgroup.name) : subgroup.name != null) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }
}
