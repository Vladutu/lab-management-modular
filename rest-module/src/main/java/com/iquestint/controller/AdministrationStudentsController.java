package com.iquestint.controller;

import com.iquestint.dto.StudentDto;
import com.iquestint.exception.ServiceEntityAlreadyExistsException;
import com.iquestint.exception.ServiceEntityNotFoundException;
import com.iquestint.exception.student.ControllerStudentAlreadyExistsException;
import com.iquestint.exception.student.ControllerStudentBindingException;
import com.iquestint.exception.student.ControllerStudentFieldsNotFoundException;
import com.iquestint.exception.student.ControllerStudentNotFoundException;
import com.iquestint.service.AdministrationStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.util.List;

/**
 * @author vladu
 */
@RestController
public class AdministrationStudentsController {

    @Autowired
    private AdministrationStudentService administrationStudentService;

    @RequestMapping(value = "/students", method = RequestMethod.GET)
    public ResponseEntity<List<StudentDto>> getAllStudents() {
        List<StudentDto> students = administrationStudentService.getAllStudents();
        if (students.isEmpty()) {
            return new ResponseEntity<List<StudentDto>>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<List<StudentDto>>(students, HttpStatus.OK);
    }

    @RequestMapping(value = "/students", method = RequestMethod.POST)
    public ResponseEntity<Void> createStudent(@Valid @RequestBody StudentDto student, BindingResult bindingResult,
        UriComponentsBuilder uriComponentsBuilder)
        throws ControllerStudentBindingException, ControllerStudentFieldsNotFoundException,
        ControllerStudentAlreadyExistsException {
        if (bindingResult.hasErrors()) {
            throw new ControllerStudentBindingException(bindingResult.getFieldErrors());
        }

        try {
            administrationStudentService.saveStudent(student);
        } catch (ServiceEntityNotFoundException e) {
            throw new ControllerStudentFieldsNotFoundException();
        } catch (ServiceEntityAlreadyExistsException e) {
            throw new ControllerStudentAlreadyExistsException();
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(uriComponentsBuilder.path("/students/{pnc}").buildAndExpand(student.getPnc()).toUri());

        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/students/{studentPnc}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteStudent(@PathVariable String studentPnc)
        throws ControllerStudentNotFoundException {
        try {
            administrationStudentService.deleteStudent(studentPnc);
        } catch (ServiceEntityNotFoundException e) {
            throw new ControllerStudentNotFoundException();
        }

        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "/students/{studentPnc}", method = RequestMethod.PUT)
    public ResponseEntity<StudentDto> updateStudent(@PathVariable String studentPnc, @RequestBody StudentDto student)
        throws ControllerStudentNotFoundException {
        try {
            student.setPnc(studentPnc);
            administrationStudentService.updateStudent(student);
        } catch (ServiceEntityNotFoundException e) {
            throw new ControllerStudentNotFoundException();
        }

        StudentDto updatedStudent = null;
        try {
            updatedStudent = administrationStudentService.getStudentByPnc(studentPnc);
        } catch (ServiceEntityNotFoundException ignored) {
        }

        return new ResponseEntity<StudentDto>(updatedStudent, HttpStatus.OK);
    }
}
