package com.iquestint.mapper;

import com.iquestint.dto.StudentDto;
import com.iquestint.model.Student;
import org.modelmapper.PropertyMap;

/**
 * This class is used by ModelMapper to map from Student class to StudentDto class.
 *
 * @author Georgian Vladutu
 */
public class StudentToStudentDtoMap extends PropertyMap<Student, StudentDto> {

    @Override
    protected void configure() {
        map(source.getPnc(), destination.getPnc());
        map(source.getFirstName(), destination.getFirstName());
        map(source.getLastName(), destination.getLastName());
        map(source.getSection().getName(), destination.getSection());
        map(source.getGroup().getName(), destination.getGroup());
        map(source.getSubgroup().getName(), destination.getSubgroup());
        map(source.getEmail(), destination.getEmail());
        map(source.getYear().getValue(), destination.getYear());
        map(source.getSemester().getValue(), destination.getSemester());
    }
}
