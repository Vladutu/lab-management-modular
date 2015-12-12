package com.iquestint.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * This is an model entity that maps the SECTION table.
 *
 * @author Georgian Vladutu
 */
@Entity
@Getter
@Setter
public class Section {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;

    @Basic
    @Column(name = "NAME", unique = true, nullable = false)
    @Size(min = 2, max = 10)
    private String name;

    @OneToMany(mappedBy = "section")
    private List<Student> students;

    @OneToMany(mappedBy = "section")
    private List<Laboratory> laboratories;

    public Section() {

    }

    public Section(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Section)) {
            return false;
        }

        Section section = (Section) o;

        return id == section.id && !(name != null ? !name.equals(section.name) : section.name != null);

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }
}