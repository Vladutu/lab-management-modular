package com.iquestint.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

/**
 * This is an model entity that maps the GRADE table.
 *
 * @author Georgian Vladutu
 */
@Entity
@Table(name = "GRADE")
@Getter
@Setter
public class Grade {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Basic
    @Column(name = "VALUE", nullable = false)
    private Integer value;

    @Basic
    @Column(name = "DATE", nullable = false)
    private LocalDate date;

    @ManyToOne
    @JoinColumn(name = "STUDENT_PNC", referencedColumnName = "PNC", nullable = false)
    private Student student;

    @ManyToOne
    @JoinColumn(name = "LABORATORY_PNC", referencedColumnName = "ID", nullable = false)
    private Laboratory laboratory;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Grade)) {
            return false;
        }

        Grade grade = (Grade) o;

        if (id != grade.id) {
            return false;
        }
        if (value != null ? !value.equals(grade.value) : grade.value != null) {
            return false;
        }
        if (date != null ? !date.equals(grade.date) : grade.date != null) {
            return false;
        }
        if (student.getPnc() != null ? !student.getPnc().equals(grade.student.getPnc()) :
            grade.student.getPnc() != null) {
            return false;
        }

        return laboratory.getId() == grade.laboratory.getId();

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (value != null ? value.hashCode() : 0);
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (student.getPnc() != null ? student.getPnc().hashCode() : 0);
        result = 31 * result + laboratory.getId();
        return result;
    }
}
