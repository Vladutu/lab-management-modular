package com.iquestint.dto;

import com.iquestint.validation.GroupExists;
import com.iquestint.validation.SectionExists;
import com.iquestint.validation.SubgroupExists;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * This class is a dto that is passed to the view.
 *
 * @author Georgian Vladutu
 */
@Getter
@Setter
public class StudentDto extends PersonDto {

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

}
