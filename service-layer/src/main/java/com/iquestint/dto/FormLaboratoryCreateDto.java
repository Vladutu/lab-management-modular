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
public class FormLaboratoryCreateDto {

    private List<HourDto> hours = new ArrayList<>();

    private List<DayDto> days = new ArrayList<>();

    private List<RoomDto> rooms = new ArrayList<>();

    private List<GroupDto> groups = new ArrayList<>();

    private List<SubgroupDto> subgroups = new ArrayList<>();

    private List<WeeklyOccurrenceDto> weeklyOccurrences = new ArrayList<>();

    private List<FormProfessorDto> professors = new ArrayList<>();
}
