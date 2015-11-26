package com.iquestint.controller;

import com.iquestint.dto.FormStudentDto;
import com.iquestint.dto.StudentDto;
import com.iquestint.exception.ServiceEntityAlreadyExistsException;
import com.iquestint.exception.ServiceEntityNotFoundException;
import com.iquestint.populator.FormStudentDtoPopulator;
import com.iquestint.service.StudentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

/**
 * This class is a controller used for operations on Student objects.
 *
 * @author Georgian Vladutu
 */
@Controller
@RequestMapping("/")
public class StudentsController {

    @Autowired
    private StudentService studentService;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    FormStudentDtoPopulator formStudentDtoPopulator;

    /**
     * Returns all existing students.
     *
     * @param model ModelMap
     * @return String
     */
    @RequestMapping(value = "/admin/students", method = RequestMethod.GET)
    public String getStudents(ModelMap model) {
        List<StudentDto> studentDtos = studentService.getAllStudents();
        model.addAttribute("students", studentDtos);

        return "listStudents";
    }

    /**
     * Returns a form to add a new student.
     *
     * @param model ModelMap
     * @return String
     */
    @RequestMapping(value = "/admin/students/new", method = RequestMethod.GET)
    public String newStudent(ModelMap model) {
        FormStudentDto formStudentDto = new FormStudentDto();
        studentService.initializeFormStudentDto(formStudentDto);

        model.addAttribute("formStudentDto", formStudentDto);

        return "createStudent";
    }

    /**
     * This method checks if all the student's fields are valid and saves it. If at least one of the fields are not valid
     * the page returned will be the same.
     *
     * @param studentDto         student model
     * @param bindingResult      BindingResult
     * @param model              ModelMap
     * @param redirectAttributes RequestAttributes
     * @return String
     */
    @RequestMapping(value = "/admin/students/new", method = RequestMethod.POST)
    public String saveStudent(@Valid StudentDto studentDto, BindingResult bindingResult, ModelMap model,
        RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            FormStudentDto formStudentDto = new FormStudentDto();
            studentService.initializeFormStudentDto(formStudentDto);
            formStudentDtoPopulator.populatePersonSpecificFields(formStudentDto, studentDto);

            model.addAttribute("formStudentDto", formStudentDto);

            return "createStudent";
        }

        try {
            studentService.saveStudent(studentDto);
        } catch (ServiceEntityNotFoundException ignored) {
        } catch (ServiceEntityAlreadyExistsException e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Student already exists");

            return "redirect:/admin/students/new";
        }

        return "redirect:/admin/students";
    }

    /**
     * Deletes the students whose pnc is studentPnc. If the student doesn't exists it will return an error message.
     *
     * @param studentPnc         the pnc of the student
     * @param model              ModelMap
     * @param redirectAttributes RedirectAttributes
     * @return String
     */
    @RequestMapping(value = "/admin/students/delete/{studentPnc}", method = RequestMethod.GET)
    public String deleteStudent(@PathVariable String studentPnc, ModelMap model,
        RedirectAttributes redirectAttributes) {
        try {
            studentService.deleteStudent(studentPnc);
        } catch (ServiceEntityNotFoundException e) {
            redirectAttributes.addFlashAttribute("errorMessage", "The student does not exists or no longer exists");
        }

        return "redirect:/admin/students";
    }

    /**
     * Returns a form to update a student whose pnc is studentPnc.
     *
     * @param studentPnc         the pnc of the student
     * @param model              ModelMap
     * @param redirectAttributes RedirectAttributes
     * @return String
     */
    @RequestMapping(value = "/admin/students/edit/{studentPnc}", method = RequestMethod.GET)
    public String editStudent(@PathVariable String studentPnc, ModelMap model, RedirectAttributes redirectAttributes) {
        try {
            StudentDto studentDto = studentService.getStudentByPnc(studentPnc);

            FormStudentDto formStudentDto = modelMapper.map(studentDto, FormStudentDto.class);
            studentService.initializeFormStudentDto(formStudentDto);

            model.addAttribute("formStudentDto", formStudentDto);

            return "updateStudent";
        } catch (ServiceEntityNotFoundException e) {
            redirectAttributes.addFlashAttribute("errorMessage", "The student does not exists or no longer exists");

            return "redirect:/admin/students";
        }
    }

    /**
     * Checks if the student model is valid, then updates it. If the student model is not valid the same page will be returned.
     *
     * @param studentDto         the student model
     * @param bindingResult      BindingResult
     * @param model              ModelMap
     * @param studentPnc         the pnc of the student
     * @param redirectAttributes RedirectAttributes
     * @return String
     */
    @RequestMapping(value = "/admin/students/edit/{studentPnc}", method = RequestMethod.POST)
    public String updateStudent(@Valid StudentDto studentDto, BindingResult bindingResult, ModelMap model,
        @PathVariable String studentPnc, RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            return "updateStudent";
        }

        try {
            studentService.updateStudent(studentDto);

            return "redirect:/admin/students";
        } catch (ServiceEntityNotFoundException e) {
            redirectAttributes.addFlashAttribute("errorMessage", "The student does not exists or no longer exists");

            return "redirect:/admin/students";
        }
    }

}
