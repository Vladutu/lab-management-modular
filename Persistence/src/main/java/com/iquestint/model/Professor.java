package com.iquestint.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * @author vladu
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

    @Basic
    @Column(name = "EMAIL")
    private String email;

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
        if (office != null ? !office.equals(professor.office) : professor.office != null) {
            return false;
        }
        return !(email != null ? !email.equals(professor.email) : professor.email != null);

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (position != null ? position.hashCode() : 0);
        result = 31 * result + (office != null ? office.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        return result;
    }
}
