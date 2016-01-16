package com.iquestint.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

/**
 * This is an model entity that maps the ATTENDANCE table.
 *
 * @author Georgian Vladutu
 */
@Entity
@Table(name = "ATTENDANCE")
@Getter
@Setter
public class Attendance {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Basic
    @Column(name = "DATE", nullable = false)
    private LocalDate date;

    @ManyToOne
    @JoinColumn(name = "STUDENT_PNC", referencedColumnName = "PNC", nullable = false)
    private Student student;

    @ManyToOne
    @JoinColumn(name = "LABORATORY_ID", referencedColumnName = "ID", nullable = false)
    private Laboratory laboratory;

    public Attendance() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Attendance)) {
            return false;
        }

        Attendance that = (Attendance) o;

        if (id != that.id) {
            return false;
        }
        if (date != null ? !date.equals(that.date) : that.date != null) {
            return false;
        }
        if (student.getPnc() != null ? !student.getPnc().equals(that.student.getPnc()) :
            that.student.getPnc() != null) {
            return false;
        }
        return laboratory.getId() == that.laboratory.getId();

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (student.getPnc() != null ? student.getPnc().hashCode() : 0);
        result = 31 * result + laboratory.getId();
        return result;
    }
}
