package com.iquestint.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author vladu
 */
@Getter
@Setter
public class FormUserDto {

    private String pnc = "";

    private String firstName = "";

    private String lastName = "";

    private String password = "";

    private String email = "";

    private String userType = "";

    private String userState = "";

    private List<UserTypeDto> types = new ArrayList<>();
}
