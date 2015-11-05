package com.iquestint.model;

import javax.persistence.*;
import java.util.List;

/**
 * This is an model entity that maps the SECTION table.
 *
 * @author Georgian Vladutu
 */
@Entity
public class Section {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SCT_ID")
    private int id;

    @Basic
    @Column(name = "NAME", unique = true)
    private String name;

    @OneToMany(mappedBy = "section")
    private List<Student> students;

    public Section() {

    }

    public Section(String name) {
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

        Section section = (Section) o;

        if (id != section.id) {
            return false;
        }
        if (name != null ? !name.equals(section.name) : section.name != null) {
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
