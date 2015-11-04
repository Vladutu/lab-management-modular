package com.iquestint.model;

import javax.persistence.*;

/**
 * @author vladu
 */
@Entity
public class Student {

    @Id
    @Column(name = "STD_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Basic
    @Column(name = "FIRST_NAME")
    private String firstName;

    @Basic
    @Column(name = "LAST_NAME")
    private String lastName;

    @ManyToOne(cascade = { CascadeType.ALL })
    @JoinColumn(name = "SECTION_ID", referencedColumnName = "SCT_ID", nullable = false)
    private Section section;

    @ManyToOne(cascade = { CascadeType.ALL })
    @JoinColumn(name = "GROUP_ID", referencedColumnName = "GRP_ID", nullable = false)
    private Group group;

    @ManyToOne(cascade = { CascadeType.ALL })
    @JoinColumn(name = "SUBGROUP_ID", referencedColumnName = "SGP_ID", nullable = false)
    private Subgroup subgroup;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Student student = (Student) o;

        if (id != student.id) {
            return false;
        }
        if (firstName != null ? !firstName.equals(student.firstName) : student.firstName != null) {
            return false;
        }
        if (lastName != null ? !lastName.equals(student.lastName) : student.lastName != null) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        return result;
    }

    public Section getSection() {
        return section;
    }

    public void setSection(Section section) {
        this.section = section;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public Subgroup getSubgroup() {
        return subgroup;
    }

    public void setSubgroup(Subgroup subgroup) {
        this.subgroup = subgroup;
    }
}
