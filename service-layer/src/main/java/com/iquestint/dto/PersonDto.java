package com.iquestint.dto;

import com.iquestint.validation.Email;
import com.iquestint.validation.Pnc;
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
public class PersonDto {

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
    @Email
    @Size(min = 10, max = 50)
    private String email;
}
