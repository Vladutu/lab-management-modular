package com.iquestint.populator;

import com.iquestint.dto.FormStudentDto;
import com.iquestint.dto.StudentDto;
import org.springframework.stereotype.Component;

/**
 * @author vladu
 */
@Component
public class FormStudentDtoPopulator {

    public void populatePersonSpecificFields(FormStudentDto formStudentDto, StudentDto studentDto) {
        formStudentDto.setLastName(studentDto.getLastName());
        formStudentDto.setEmail(studentDto.getEmail());
        formStudentDto.setFirstName(studentDto.getFirstName());
        formStudentDto.setPnc(studentDto.getPnc());
    }
}
