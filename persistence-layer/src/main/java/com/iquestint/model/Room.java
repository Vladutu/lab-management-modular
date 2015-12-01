package com.iquestint.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

/**
 * This is an model entity that maps the ROOM table.
 *
 * @author Georgian Vladutu
 */
@Entity
@Table(name = "ROOM")
@Getter
@Setter
public class Room {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Basic
    @Column(name = "NAME")
    private String name;

    @OneToMany(mappedBy = "room")
    private List<Laboratory> laboratories;

    public Room() {
    }

    public Room(String name) {
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

        Room room = (Room) o;

        if (id != room.id) {
            return false;
        }
        if (name != null ? !name.equals(room.name) : room.name != null) {
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
