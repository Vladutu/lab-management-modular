package com.iquestint.mapper;

import com.iquestint.dto.StudentDto;
import com.iquestint.model.Student;
import org.modelmapper.PropertyMap;

/**
 * @author vladu
 */
public class StudentDtoToStudentMap extends PropertyMap<StudentDto, Student> {

    @Override
    protected void configure() {
        map(source.getPnc(), destination.getPnc());
        map(source.getFirstName(), destination.getFirstName());
        map(source.getLastName(), destination.getLastName());
        map(source.getSection(), destination.getSection().getName());
        map(source.getGroup(), destination.getGroup().getName());
        map(source.getSubgroup(), destination.getSubgroup().getName());
        map(source.getEmail(), destination.getEmail());
        map(source.getYear(), destination.getYear().getValue());
        map(source.getSemester(), destination.getSemester().getValue());

    }
}
