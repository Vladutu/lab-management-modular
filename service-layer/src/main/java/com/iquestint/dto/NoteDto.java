package com.iquestint.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Size;

/**
 * This class represents a data transfer object.
 *
 * @author Georgian Vladutu
 */
@Getter
@Setter
public class NoteDto {

    private int id;

    @Size(min = 3, max = 250)
    private String message;

    private String date;
}
