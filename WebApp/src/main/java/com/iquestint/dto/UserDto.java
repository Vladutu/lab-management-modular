package com.iquestint.dto;

import com.iquestint.validation.Email;
import com.iquestint.validation.PersonExists;
import com.iquestint.validation.Pnc;
import com.iquestint.validation.TypeExists;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author vladu
 */
@Getter
@Setter
public class UserDto {

    @NotNull
    @Pnc
    @PersonExists
    private String pnc = "";

    @NotNull
    @Size(min = 3, max = 30)
    private String firstName = "";

    @NotNull
    @Size(min = 3, max = 30)
    private String lastName = "";

    @NotNull
    @Size(min = 6, max = 40)
    private String password = "";

    @NotNull
    @Size(min = 10, max = 40)
    @Email
    private String email = "";

    @NotNull
    @TypeExists
    private String userType = "";
}
