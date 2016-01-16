package com.iquestint.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * @author vladu
 */
@Getter
@Setter
public class DocumentWithContentDto extends DocumentDto {

    private byte[] content;
}
