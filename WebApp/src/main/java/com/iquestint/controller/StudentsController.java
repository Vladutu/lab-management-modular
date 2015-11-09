package com.iquestint.controller;

import com.iquestint.dto.*;
import com.iquestint.exception.ServiceEntityNotFoundException;
import com.iquestint.model.Group;
import com.iquestint.model.Section;
import com.iquestint.model.Student;
import com.iquestint.model.Subgroup;
import com.iquestint.service.GroupService;
import com.iquestint.service.SectionService;
import com.iquestint.service.StudentService;
import com.iquestint.service.SubgroupService;
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
    private SectionService sectionService;

    @Autowired
    private GroupService groupService;

    @Autowired
    private SubgroupService subgroupService;

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

    @RequestMapping(value = "/students/new", method = RequestMethod.GET)
    public String newStudent(ModelMap model) {
        StudentDto studentDto = new StudentDto();
        List<Section> sections = sectionService.getAllSections();
        List<Group> groups = groupService.getAllGroups();
        List<Subgroup> subgroups = subgroupService.getAllSubgroups();

        List<SectionDto> sectionDtos = new ArrayList<>();
        for (Section section : sections) {
            sectionDtos.add(modelMapper.map(section, SectionDto.class));
        }

        List<GroupDto> groupDtos = new ArrayList<>();
        for (Group group : groups) {
            groupDtos.add(modelMapper.map(group, GroupDto.class));
        }

        List<SubgroupDto> subgroupDtos = new ArrayList<>();
        for (Subgroup subgroup : subgroups) {
            subgroupDtos.add(modelMapper.map(subgroup, SubgroupDto.class));
        }

        StudentWrapper studentWrapper = new StudentWrapper();
        studentWrapper.setStudentDto(studentDto);
        studentWrapper.setSectionDtos(sectionDtos);
        studentWrapper.setGroupDtos(groupDtos);
        studentWrapper.setSubgroupDtos(subgroupDtos);

        model.addAttribute("studentWrapper", studentWrapper);

        return "createStudent";
    }

    @RequestMapping(value = "/students/delete/{studentId}", method = RequestMethod.GET)
    public String deleteStudent(@PathVariable int studentId) {
        try {
            studentService.deleteStudent(studentId);
        }
        catch (ServiceEntityNotFoundException e) {
            e.printStackTrace();
        }

        return "redirect:/students";
    }

    @RequestMapping(value = "/students/edit/{studentId}", method = RequestMethod.GET)
    public String editStudent(@PathVariable int studentId, ModelMap model) {
        try {
            Student student = studentService.getStudentById(studentId);
            StudentDto studentDto = modelMapper.map(student, StudentDto.class);

            model.addAttribute("studentDto", studentDto);

            return "updateStudent";
        }
        catch (ServiceEntityNotFoundException e) {
            e.printStackTrace();
        }

        return "error";
    }

    @RequestMapping(value = "/students/edit/{studentId}", method = RequestMethod.POST)
    public String updateStudent(@Valid StudentDto studentDto, BindingResult bindingResult, ModelMap model,
        @PathVariable int studentId) {

        if (bindingResult.hasErrors()) {
            return "updateStudent";
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
