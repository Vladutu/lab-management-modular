package com.iquestint.populator;

import com.iquestint.dao.*;
import com.iquestint.dto.StudentDto;
import com.iquestint.exception.DaoEntityNotFoundException;
import com.iquestint.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author vladu
 */
@Component
public class StudentPopulator {

    @Autowired
    private GroupDao groupDao;

    @Autowired
    private SectionDao sectionDao;

    @Autowired
    private SubgroupDao subgroupDao;

    @Autowired
    private YearDao yearDao;

    @Autowired
    private SemesterDao semesterDao;

    public void populateStudentSpecificFields(Student student, StudentDto studentDto)
        throws DaoEntityNotFoundException {
        Section section = sectionDao.getSectionByName(studentDto.getSection());
        Group group = groupDao.getGroupByName(studentDto.getGroup());
        Subgroup subgroup = subgroupDao.getSubgroupByName(studentDto.getSubgroup());
        Year year = yearDao.getYearByValue(studentDto.getYear());
        Semester semester = semesterDao.getSemesterByValue(studentDto.getSemester());

        student.setGroup(group);
        student.setSubgroup(subgroup);
        student.setSection(section);
        student.setYear(year);
        student.setSemester(semester);
    }

    public void populatePersonSpecificFields(Student student, StudentDto studentDto) {
        student.setFirstName(studentDto.getFirstName());
        student.setLastName(studentDto.getLastName());
        student.setEmail(studentDto.getEmail());
        student.setPnc(studentDto.getPnc());
    }
}
