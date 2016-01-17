package com.iquestint.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * This is an model entity that maps the GROUP_TABLE table.
 *
 * @author Georgian Vladutu
 */
@Entity
@Table(name = "group_table", uniqueConstraints = {
    @UniqueConstraint(columnNames = "name") })
@Getter
@Setter
public class Group {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;

    @Basic
    @Column(name = "NAME", unique = true, nullable = false)
    @Size(max = 15)
    private String name;

    @OneToMany(mappedBy = "group")
    private List<Student> students;

    @OneToMany(mappedBy = "group")
    private List<Laboratory> laboratories;

    public Group() {
    }

    public Group(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Group)) {
            return false;
        }

        Group group = (Group) o;

        return id == group.id && !(name != null ? !name.equals(group.name) : group.name != null);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }
}
