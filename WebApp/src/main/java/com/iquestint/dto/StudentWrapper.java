package com.iquestint.dto;

import java.util.List;

/**
 * @author vladu
 */
public class StudentWrapper {

    private StudentDto studentDto;

    private List<SectionDto> sectionDtos;

    private List<GroupDto> groupDtos;

    private List<SubgroupDto> subgroupDtos;

    public StudentDto getStudentDto() {
        return studentDto;
    }

    public void setStudentDto(StudentDto studentDto) {
        this.studentDto = studentDto;
    }

    public List<SectionDto> getSectionDtos() {
        return sectionDtos;
    }

    public void setSectionDtos(List<SectionDto> sectionDtos) {
        this.sectionDtos = sectionDtos;
    }

    public List<GroupDto> getGroupDtos() {
        return groupDtos;
    }

    public void setGroupDtos(List<GroupDto> groupDtos) {
        this.groupDtos = groupDtos;
    }

    public List<SubgroupDto> getSubgroupDtos() {
        return subgroupDtos;
    }

    public void setSubgroupDtos(List<SubgroupDto> subgroupDtos) {
        this.subgroupDtos = subgroupDtos;
    }
}
