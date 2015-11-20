package com.iquestint.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * @author vladu
 */
@Getter
@Setter
public class LaboratoryDto {

    private Integer id;

    private String name;

    private Integer from;

    private Integer to;

    private String room;

    private Integer day;

    private String section;

    private String group;

    private String subgroup;

    private Integer year;

    private Integer semester;

    private String weeklyOccurrence;

    private ProfessorDto professorDto;
}
