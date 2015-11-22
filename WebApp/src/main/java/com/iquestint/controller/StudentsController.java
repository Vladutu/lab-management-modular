package com.iquestint.controller;

import com.iquestint.dto.*;
import com.iquestint.exception.ServiceEntityAlreadyExistsException;
import com.iquestint.exception.ServiceEntityNotFoundException;
import com.iquestint.mapper.*;
import com.iquestint.model.*;
import com.iquestint.service.*;
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
    private SectionService sectionService;

    @Autowired
    private GroupService groupService;

    @Autowired
    private SubgroupService subgroupService;

    @Autowired
    private YearService yearService;

    @Autowired
    private SemesterService semesterService;

    @Autowired
    private StudentMapper studentMapper;

    @Autowired
    private YearMapper yearMapper;

    @Autowired
    private SemesterMapper semesterMapper;

    @Autowired
    private SectionMapper sectionMapper;

    @Autowired
    private GroupMapper groupMapper;

    @Autowired
    private SubgroupMapper subgroupMapper;

    /**
     * Returns all existing students.
     *
     * @param model ModelMap
     * @return String
     */
    @RequestMapping(value = "/admin/students", method = RequestMethod.GET)
    public String getStudents(ModelMap model) {
        List<Student> students = studentService.getAllStudents();
        List<StudentDto> studentsDto = studentMapper.mapList(students);

        model.addAttribute("students", studentsDto);

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
        initializeStudentDto(model);

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
            initializeDtoLists(model);
            return "createStudent";
        }

        Student student = studentMapper.reverseMap(studentDto);
        try {
            studentService.saveStudent(student);
        }
        catch (ServiceEntityNotFoundException ignored) {
        }
        catch (ServiceEntityAlreadyExistsException e) {
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
        }
        catch (ServiceEntityNotFoundException e) {
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
            Student student = studentService.getStudentByPnc(studentPnc);
            StudentDto studentDto = studentMapper.map(student);
            model.addAttribute("studentDto", studentDto);

            initializeDtoLists(model);

            return "updateStudent";
        }
        catch (ServiceEntityNotFoundException e) {
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
            Student student = studentMapper.reverseMap(studentDto);
            studentService.updateStudent(student);

            return "redirect:/admin/students";
        }
        catch (ServiceEntityNotFoundException e) {
            redirectAttributes.addFlashAttribute("errorMessage", "The student does not exists or no longer exists");

            return "redirect:/admin/students";
        }
    }

    private void initializeStudentDto(ModelMap model) {
        StudentDto studentDto = new StudentDto();
        model.addAttribute("studentDto", studentDto);
        initializeDtoLists(model);
    }

    private void initializeDtoLists(ModelMap model) {
        List<Section> sections = sectionService.getAllSections();
        List<Group> groups = groupService.getAllGroups();
        List<Subgroup> subgroups = subgroupService.getAllSubgroups();
        List<Year> years = yearService.getAllYears();
        List<Semester> semesters = semesterService.getAllSemesters();

        List<SectionDto> sectionDtos = sectionMapper.mapList(sections);
        List<GroupDto> groupDtos = groupMapper.mapList(groups);
        List<SubgroupDto> subgroupDtos = subgroupMapper.mapList(subgroups);
        List<YearDto> yearDtos = yearMapper.mapList(years);
        List<SemesterDto> semesterDtos = semesterMapper.mapList(semesters);

        model.addAttribute("sectionDtos", sectionDtos);
        model.addAttribute("groupDtos", groupDtos);
        model.addAttribute("subgroupDtos", subgroupDtos);
        model.addAttribute("yearDtos", yearDtos);
        model.addAttribute("semesterDtos", semesterDtos);
    }

}
