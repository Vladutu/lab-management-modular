package com.iquestint.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * This class represents a data transfer object.
 *
 * @author Georgian Vladutu
 */
@Getter
@Setter
public class UnregisteredUserDto {

    private String firstName = "";

    private String lastName = "";

    private String email = "";

    private String userType = "";
}
