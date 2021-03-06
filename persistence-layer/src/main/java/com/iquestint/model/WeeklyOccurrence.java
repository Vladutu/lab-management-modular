package com.iquestint.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * This is an model entity that maps the WEEKLY_OCCURRENCE table.
 *
 * @author Georgian Vladutu
 */
@Entity
@Table(name = "WEEKLY_OCCURRENCE")
@Getter
@Setter
public class WeeklyOccurrence {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Basic
    @Column(name = "NAME", nullable = false)
    @Size(min = 15, max = 20)
    private String name;

    @OneToMany(mappedBy = "weeklyOccurrence")
    private List<Laboratory> laboratories;

    public WeeklyOccurrence() {
    }

    public WeeklyOccurrence(String name) {
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

        WeeklyOccurrence that = (WeeklyOccurrence) o;

        if (id != that.id) {
            return false;
        }
        if (name != null ? !name.equals(that.name) : that.name != null) {
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
