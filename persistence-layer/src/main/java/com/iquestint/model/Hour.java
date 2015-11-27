package com.iquestint.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

/**
 * @author vladu
 */
@Entity
@Table(name = "HOUR", uniqueConstraints = {
    @UniqueConstraint(columnNames = "value") })
@Getter
@Setter
public class Hour {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Basic
    @Column(name = "VALUE")
    private Integer value;

    @OneToMany(mappedBy = "from")
    private List<Laboratory> laboratoriesFrom;

    @OneToMany(mappedBy = "to")
    private List<Laboratory> laboratoriesTo;

    public Hour() {
    }

    public Hour(Integer value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Hour)) {
            return false;
        }

        Hour hour = (Hour) o;

        if (id != hour.id) {
            return false;
        }
        return !(value != null ? !value.equals(hour.value) : hour.value != null);

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (value != null ? value.hashCode() : 0);
        return result;
    }
}