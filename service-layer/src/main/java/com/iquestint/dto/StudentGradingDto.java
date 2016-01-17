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
public class StudentGradingDto {

    private String pnc;

    private String firstName;

    private String lastName;

    private String grade;
}
