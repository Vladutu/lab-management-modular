package com.iquestint.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

/**
 * This is an model entity that maps the DAY table.
 *
 * @author Georgian Vladutu
 */
@Entity
@Table(name = "DAY", uniqueConstraints = {
    @UniqueConstraint(columnNames = "value") })
@Getter
@Setter
public class Day {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Basic
    @Column(name = "VALUE", nullable = false)
    private Integer value;

    @OneToMany(mappedBy = "day")
    private List<Laboratory> laboratories;

    public Day() {
    }

    public Day(Integer value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Day)) {
            return false;
        }

        Day day = (Day) o;

        if (id != day.id) {
            return false;
        }
        return value.equals(day.value);

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + value.hashCode();
        return result;
    }
}
