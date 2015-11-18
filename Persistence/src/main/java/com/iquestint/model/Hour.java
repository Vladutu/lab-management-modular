package com.iquestint.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

/**
 * @author vladu
 */
@Entity
@Table(name = "HOUR")
@Getter
@Setter
public class Hour {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Basic
    @Column(name = "NAME")
    private String name;

    @OneToMany(mappedBy = "from")
    private List<Laboratory> laboratoriesFrom;

    @OneToMany(mappedBy = "to")
    private List<Laboratory> laboratoriesTo;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Hour hour = (Hour) o;

        if (id != hour.id) {
            return false;
        }
        if (name != null ? !name.equals(hour.name) : hour.name != null) {
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