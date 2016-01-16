package com.iquestint.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * @author vladu
 */

@Getter
@Setter
public class DocumentDto {

    private int id;

    private String name;

    private String type;

    private int laboratoryId;
}
