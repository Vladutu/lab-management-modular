package com.iquestint.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

/**
 * This is an model entity that maps the LABORATORY table.
 *
 * @author Georgian Vladutu
 */
@Entity
@Table(name = "LABORATORY")
@Getter
@Setter
public class Laboratory {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Basic
    @Column(name = "NAME")
    private String name;

    @ManyToOne
    @JoinColumn(name = "FROM_ID", referencedColumnName = "ID", nullable = false)
    private Hour from;

    @ManyToOne
    @JoinColumn(name = "TO_ID", referencedColumnName = "ID", nullable = false)
    private Hour to;

    @ManyToOne
    @JoinColumn(name = "PROFESSOR_PNC", referencedColumnName = "PNC", nullable = false)
    private Professor professor;

    @ManyToOne
    @JoinColumn(name = "ROOM_ID", referencedColumnName = "ID", nullable = false)
    private Room room;

    @ManyToOne
    @JoinColumn(name = "DAY_ID", referencedColumnName = "ID", nullable = false)
    private Day day;

    @ManyToOne
    @JoinColumn(name = "SECTION_ID", referencedColumnName = "ID", nullable = false)
    private Section section;

    @ManyToOne
    @JoinColumn(name = "GROUP_ID", referencedColumnName = "ID", nullable = false)
    private Group group;

    @ManyToOne
    @JoinColumn(name = "SUBGROUP_ID", referencedColumnName = "ID", nullable = false)
    private Subgroup subgroup;

    @ManyToOne
    @JoinColumn(name = "YEAR_ID", referencedColumnName = "ID", nullable = false)
    private Year year;

    @ManyToOne
    @JoinColumn(name = "SEMESTER_ID", referencedColumnName = "ID", nullable = false)
    private Semester semester;

    @ManyToOne
    @JoinColumn(name = "WEEKLY_OCCURRENCE_ID", referencedColumnName = "ID", nullable = false)
    private WeeklyOccurrence weeklyOccurrence;

    @ManyToMany
    @JoinTable(name = "STUDENT_LABORATORY",
        joinColumns = @JoinColumn(name = "LABORATORY_ID", referencedColumnName = "ID", nullable = false),
        inverseJoinColumns = @JoinColumn(name = "STUDENT_PNC", referencedColumnName = "PNC", nullable = false))
    private List<Student> students;

    @OneToMany(mappedBy = "laboratory")
    private List<Grade> grades;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Laboratory that = (Laboratory) o;

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