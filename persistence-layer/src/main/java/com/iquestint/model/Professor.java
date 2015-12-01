package com.iquestint.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

/**
 * This is an model entity that maps the PROFESSOR table.
 *
 * @author Georgian Vladutu
 */
@Entity
@Table(name = "PROFESSOR")
@PrimaryKeyJoinColumn(name = "PNC")
@Getter
@Setter
public class Professor extends Person {

    @Basic
    @Column(name = "POSITION")
    private String position;

    @Basic
    @Column(name = "OFFICE")
    private String office;

    @OneToMany(mappedBy = "professor")
    private List<Laboratory> laboratories;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Professor)) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }

        Professor professor = (Professor) o;

        if (position != null ? !position.equals(professor.position) : professor.position != null) {
            return false;
        }

        if (getFirstName() != null ? !getFirstName().equals(professor.getFirstName()) :
            professor.getFirstName() != null) {
            return false;
        }

        if (getLastName() != null ? !getLastName().equals(professor.getLastName()) :
            professor.getLastName() != null) {
            return false;
        }

        if (getEmail() != null ? !getEmail().equals(professor.getEmail()) :
            professor.getEmail() != null) {
            return false;
        }

        return !(office != null ? !office.equals(professor.office) : professor.office != null);

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (position != null ? position.hashCode() : 0);
        result = 31 * result + (getFirstName() != null ? getFirstName().hashCode() : 0);
        result = 31 * result + (getLastName() != null ? getLastName().hashCode() : 0);
        result = 31 * result + (getEmail() != null ? getEmail().hashCode() : 0);
        result = 31 * result + (office != null ? office.hashCode() : 0);

        return result;
    }
}
