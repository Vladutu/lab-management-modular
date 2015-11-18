package com.iquestint.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

/**
 * @author vladu
 */
@Entity
@Table(name = "SEMESTER")
@Getter
@Setter
public class Semester {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Basic
    @Column(name = "NAME")
    private String name;

    @OneToMany(mappedBy = "semester")
    private List<Student> students;

    @OneToMany(mappedBy = "semester")
    private List<Laboratory> laboratories;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Semester semester = (Semester) o;

        if (id != semester.id) {
            return false;
        }
        if (name != null ? !name.equals(semester.name) : semester.name != null) {
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
}
