package com.iquestint.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author vladu
 */
@Getter
@Setter
public class FormLaboratoryShowDto {

    private List<SectionDto> sections;

    private List<YearDto> years;

    private List<SemesterDto> semesters;
}
