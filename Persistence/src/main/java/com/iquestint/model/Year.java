package com.iquestint.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

/**
 * @author vladu
 */
@Entity
@Table(name = "YEAR", uniqueConstraints = {
    @UniqueConstraint(columnNames = "value") })
@Getter
@Setter
public class Year {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Basic
    @Column(name = "VALUE")
    private Integer value;

    @OneToMany(mappedBy = "year")
    private List<Student> students;

    @OneToMany(mappedBy = "year")
    private List<Laboratory> laboratories;

    public Year() {
    }

    public Year(int value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Year)) {
            return false;
        }

        Year year = (Year) o;

        if (id != year.id) {
            return false;
        }
        return !(value != null ? !value.equals(year.value) : year.value != null);

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (value != null ? value.hashCode() : 0);
        return result;
    }
}