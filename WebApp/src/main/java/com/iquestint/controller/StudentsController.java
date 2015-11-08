package com.iquestint.controller;

import com.iquestint.dto.StudentDto;
import com.iquestint.exception.ServiceEntityNotFoundException;
import com.iquestint.model.Student;
import com.iquestint.service.StudentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * @author vladu
 */
@Controller
@RequestMapping("/")
public class StudentsController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private ModelMapper modelMapper;

    @RequestMapping(value = "/students", method = RequestMethod.GET)
    public String getStudents(ModelMap model) {
        List<Student> students = studentService.getAllStudents();
        List<StudentDto> studentsDto = new ArrayList<>();

        for (Student student : students) {
            StudentDto studentDto = modelMapper.map(student, StudentDto.class);
            studentsDto.add(studentDto);
        }

        model.addAttribute("students", studentsDto);

        return "listStudents";
    }

    @RequestMapping(value = "/student/delete/{studentId}", method = RequestMethod.GET)
    public String deleteStudent(@PathVariable int studentId) {
        try {
            studentService.deleteStudent(studentId);
        }
        catch (ServiceEntityNotFoundException e) {
            e.printStackTrace();
        }

        return "redirect:/students";
    }

    @RequestMapping(value = "/student/edit/{studentId}", method = RequestMethod.GET)
    public String editStudent(@PathVariable int studentId, ModelMap model) {
        try {
            Student student = studentService.getStudentById(studentId);
            StudentDto studentDto = modelMapper.map(student, StudentDto.class);

            model.addAttribute("studentDto", studentDto);

            return "update";
        }
        catch (ServiceEntityNotFoundException e) {
            e.printStackTrace();
        }

        return "error";
    }

    @RequestMapping(value = "/student/edit/{studentId}", method = RequestMethod.POST)
    public String updateStudent(@Valid StudentDto studentDto, BindingResult bindingResult, ModelMap model,
        @PathVariable int studentId) {

        if (bindingResult.hasErrors()) {
            return "update";
        }

        try {
            Student student = modelMapper.map(studentDto, Student.class);
            studentService.updateStudent(student);

            return "redirect:/students";
        }
        catch (ServiceEntityNotFoundException e) {
            e.printStackTrace();
        }

        return "error";
    }
}
