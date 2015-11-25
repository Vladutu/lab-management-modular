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
public class FormStudentDto {

    private String pnc = "";

    private String firstName = "";

    private String lastName = "";

    private String email = "";

    private String section;

    private String group;

    private String subgroup;

    private Integer year;

    private Integer semester;

    private List<SectionDto> sections = new ArrayList<>();

    private List<GroupDto> groups = new ArrayList<>();

    private List<SubgroupDto> subgroups = new ArrayList<>();

    private List<YearDto> years = new ArrayList<>();

    private List<SemesterDto> semesters = new ArrayList<>();
}
