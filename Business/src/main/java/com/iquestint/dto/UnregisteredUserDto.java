package com.iquestint.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * @author vladu
 */
@Getter
@Setter
public class UnregisteredUserDto {

    private String firstName = "";

    private String lastName = "";

    private String email = "";

    private String userType = "";
}
