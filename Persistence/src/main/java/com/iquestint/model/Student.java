package com.iquestint.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

/**
 * This is an model entity that maps the STUDENT table.
 *
 * @author Georgian Vladutu
 */
@Entity
@Table(name = "STUDENT")
@PrimaryKeyJoinColumn(name = "PNC")
@Getter
@Setter
public class Student extends Person {

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

    @ManyToMany
    @JoinTable(name = "STUDENT_LABORATORY",
        joinColumns = @JoinColumn(name = "STUDENT_PNC", referencedColumnName = "PNC", nullable = false),
        inverseJoinColumns = @JoinColumn(name = "LABORATORY_ID", referencedColumnName = "ID", nullable = false))
    private List<Laboratory> laboratories;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Student)) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }

        Student student = (Student) o;

        if (section != null ? !section.equals(student.section) : student.section != null) {
            return false;
        }
        if (group != null ? !group.equals(student.group) : student.group != null) {
            return false;
        }

        if (getFirstName() != null ? !getFirstName().equals(student.getFirstName()) : student.getFirstName() != null) {
            return false;
        }

        if (getLastName() != null ? !getLastName().equals(student.getLastName()) : student.getLastName() != null) {
            return false;
        }

        if (getEmail() != null ? !getEmail().equals(student.getEmail()) : student.getEmail() != null) {
            return false;
        }

        return !(subgroup != null ? !subgroup.equals(student.subgroup) : student.subgroup != null);

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (section != null ? section.hashCode() : 0);
        result = 31 * result + (group != null ? group.hashCode() : 0);
        result = 31 * result + (subgroup != null ? subgroup.hashCode() : 0);
        result = 31 * result + (getFirstName() != null ? getFirstName().hashCode() : 0);
        result = 31 * result + (getEmail() != null ? getEmail().hashCode() : 0);
        result = 31 * result + (getLastName() != null ? getLastName().hashCode() : 0);

        return result;
    }
}
