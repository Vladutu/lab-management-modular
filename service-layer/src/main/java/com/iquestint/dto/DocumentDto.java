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
public class DocumentDto {

    private int id;

    private String name;

    private String type;

    private int laboratoryId;
}
