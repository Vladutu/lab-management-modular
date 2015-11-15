package com.iquestint.dto;

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
public class ProfessorDto extends PersonDto {

    @NotNull
    @Size(min = 3, max = 15)
    private String position;

    @NotNull
    @Size(min = 3, max = 10)
    private String office;

}
