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
public class LaboratoryWithStudentsDto {

    private LaboratoryDto laboratory;

    private List<StudentDto> students = new ArrayList<>();
}
