package com.iquestint.dto;

import com.iquestint.validation.Pnc;
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
public class ProfessorDto {

    @NotNull
    @Pnc
    private String pnc;

    @NotNull
    @Size(min = 3, max = 30)
    private String firstName;

    @NotNull
    @Size(min = 3, max = 30)
    private String lastName;

    @NotNull
    @Size(min = 3, max = 15)
    private String position;

    @NotNull
    @Size(min = 3, max = 10)
    private String office;

    @NotNull
    @Size(min = 10, max = 50)
    private String email;
}
