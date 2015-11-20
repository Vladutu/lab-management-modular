package com.iquestint.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

/**
 * @author vladu
 */
@Entity
@Table(name = "SEMESTER", uniqueConstraints = {
    @UniqueConstraint(columnNames = "value") })
@Getter
@Setter
public class Semester {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Basic
    @Column(name = "VALUE")
    private Integer value;

    @OneToMany(mappedBy = "semester")
    private List<Student> students;

    @OneToMany(mappedBy = "semester")
    private List<Laboratory> laboratories;

    public Semester() {
    }

    public Semester(int value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Semester)) {
            return false;
        }

        Semester semester = (Semester) o;

        if (id != semester.id) {
            return false;
        }
        return !(value != null ? !value.equals(semester.value) : semester.value != null);

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (value != null ? value.hashCode() : 0);
        return result;
    }
}
