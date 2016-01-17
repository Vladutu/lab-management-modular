package com.iquestint.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

/**
 * This is an model entity that maps the NOTE table.
 *
 * @author Georgian Vladutu
 */
@Entity
@Table(name = "NOTE")
@Getter
@Setter
public class Note {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Basic
    @Column(name = "MESSAGE", nullable = false)
    @Size(min = 3, max = 250)
    private String message;

    @Basic
    @Column(name = "DATE", nullable = false)
    private LocalDateTime date;

    @ManyToOne
    @JoinColumn(name = "LABORATORY_ID", referencedColumnName = "ID", nullable = false)
    private Laboratory laboratory;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Note)) {
            return false;
        }

        Note note = (Note) o;

        if (id != note.id) {
            return false;
        }
        if (message != null ? !message.equals(note.message) : note.message != null) {
            return false;
        }
        return !(date != null ? !date.equals(note.date) : note.date != null);

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (message != null ? message.hashCode() : 0);
        result = 31 * result + (date != null ? date.hashCode() : 0);
        return result;
    }
}
