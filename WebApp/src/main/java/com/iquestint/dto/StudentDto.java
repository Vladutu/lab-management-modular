package com.iquestint.dto;

import com.iquestint.validation.GroupExists;
import com.iquestint.validation.SectionExists;
import com.iquestint.validation.SubgroupExists;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author vladu
 */
public class StudentDto {

    private int id;

    @NotNull
    @Size(min = 3, max = 30)
    private String firstName;

    @NotNull
    @Size(min = 3, max = 30)
    private String lastName;

    @NotNull
    @SectionExists
    @Size(min = 3, max = 10)
    private String section;

    @NotNull
    @GroupExists
    @Size(min = 3, max = 10)
    private String group;

    @NotNull
    @SubgroupExists
    @Size(min = 1, max = 2)
    private String subgroup;

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

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getSubgroup() {
        return subgroup;
    }

    public void setSubgroup(String subgroup) {
        this.subgroup = subgroup;
    }
}
