package com.iquestint.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * This class represents a data transfer object.
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
