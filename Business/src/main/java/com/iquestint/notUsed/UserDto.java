package com.iquestint.notUsed;

import com.iquestint.validation.*;
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

    @NotNull
    @StateExists
    private String userState = "";
}
