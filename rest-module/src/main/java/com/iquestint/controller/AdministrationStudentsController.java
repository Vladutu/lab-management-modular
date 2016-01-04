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
 * This class is a REST controller used for operations on Student objects.
 *
 * @author Georgian Vladutu
 */
@RestController
@RequestMapping(value = "/admin")
public class AdministrationStudentsController {

    @Autowired
    private AdministrationStudentService administrationStudentService;

    /**
     * Returns all existing students.
     *
     * @return list of students
     */
    @RequestMapping(value = "/students", method = RequestMethod.GET)
    public ResponseEntity<List<StudentDto>> getAllStudents() {
        List<StudentDto> students = administrationStudentService.getAllStudents();
        if (students.isEmpty()) {
            return new ResponseEntity<List<StudentDto>>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<List<StudentDto>>(students, HttpStatus.OK);
    }

    /**
     * Saves a new student.
     *
     * @param student              student to be saved
     * @param bindingResult        bindingResult
     * @param uriComponentsBuilder uriComponentsBuilder
     * @return a corresponding status code and a message if there is an error
     * @throws ControllerStudentBindingException        if the user input and the java object cannot be bound together
     * @throws ControllerStudentFieldsNotFoundException if any the student's fields are incorrect
     * @throws ControllerStudentAlreadyExistsException  if the student already exists
     */
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

    /**
     * Deletes the students whose pnc is studentPnc.
     *
     * @param studentPnc the pnc of the student
     * @return a corresponding status code and a message if there is an error
     * @throws ControllerStudentNotFoundException if the student is not found
     */
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

    /**
     * Updates the student whose pnc is studentPnc
     *
     * @param studentPnc the pnc of the student
     * @param student    the student to be updated
     * @return a corresponding status code and a message if there is an error
     * @throws ControllerStudentNotFoundException if the student is not found
     */
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
