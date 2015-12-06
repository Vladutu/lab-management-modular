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
public class ProfessorLaboratoryDto {

    private String name;

    private List<StudentDto> students = new ArrayList<>();
}
