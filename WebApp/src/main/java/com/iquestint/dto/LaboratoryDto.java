package com.iquestint.dto;

import com.iquestint.validation.ProfessorExists;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

/**
 * @author vladu
 */
@Getter
@Setter
public class LaboratoryDto {

    private Integer id;

    @NotNull
    private String name;

    @NotNull
    private Integer from;

    @NotNull
    private Integer to;

    @NotNull
    private String room;

    @NotNull
    private Integer day;

    @NotNull
    private String section;

    @NotNull
    private String group;

    @NotNull
    private String subgroup;

    @NotNull
    private Integer year;

    @NotNull
    private Integer semester;

    @NotNull
    private String weeklyOccurrence;

    @NotNull
    @ProfessorExists
    private SimplifiedProfessorDto professorDto;
}
