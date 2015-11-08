package com.iquestint.controller;

import com.iquestint.exception.ServiceEntityNotFoundException;
import com.iquestint.model.Student;
import com.iquestint.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author vladu
 */
@Controller
@RequestMapping("/")
public class StudentsController {

    @Autowired
    StudentService studentService;

    @RequestMapping(value = "/students", method = RequestMethod.GET)
    public String getStudent(ModelMap model) {
        model.addAttribute("students", studentService.getAllStudents());

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
            model.addAttribute("student", student);
            model.addAttribute("edit", true);

            return "registration";
        }
        catch (ServiceEntityNotFoundException e) {
            e.printStackTrace();
        }

        return "error";
    }

    @RequestMapping(value = "/student/edit/{studentId}", method = RequestMethod.POST)
    public String updateStudent(Student student, ModelMap model, @PathVariable int studentId) {
        try {
            studentService.updateStudent(student);

            return "redirect:/students";
        }
        catch (ServiceEntityNotFoundException e) {
            e.printStackTrace();
        }

        return "error";
    }
}
