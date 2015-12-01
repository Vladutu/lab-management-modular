package com.iquestint.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * This class represents a data transfer object.
 *
 * @author Georgian Vladutu
 */
@Getter
@Setter
public class FormStudentDto {

    private List<SectionDto> sections = new ArrayList<>();

    private List<GroupDto> groups = new ArrayList<>();

    private List<SubgroupDto> subgroups = new ArrayList<>();

    private List<YearDto> years = new ArrayList<>();

    private List<SemesterDto> semesters = new ArrayList<>();
}
