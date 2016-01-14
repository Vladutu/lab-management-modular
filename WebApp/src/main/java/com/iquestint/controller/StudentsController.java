package com.iquestint.controller;

import com.iquestint.dto.GradeDto;
import com.iquestint.dto.LaboratoryDto;
import com.iquestint.dto.WelcomeUserDto;
import com.iquestint.exception.ServiceEntityNotFoundException;
import com.iquestint.service.StudentService;
import com.iquestint.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * @author vladu
 */
@Controller
@RequestMapping("/")
public class StudentsController {

    private static final Logger LOGGER = LoggerFactory.getLogger(StudentsController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private StudentService studentService;

    /**
     * Returns the student's home page.
     *
     * @param model ModelMap
     * @return String
     */
    @RequestMapping(value = "/student/home", method = RequestMethod.GET)
    public String getHome(ModelMap model) {
        LOGGER.info("Enter method");
        WelcomeUserDto welcomeUserDto = getPrincipal();
        model.addAttribute("welcomeUserDto", welcomeUserDto);

        List<LaboratoryDto> laboratoryDtos = studentService.getStudentLaboratories(welcomeUserDto.getPnc());
        model.addAttribute("laboratoryDtos", laboratoryDtos);

        return "student/studentHome";
    }

    /**
     * Returns the student grades at the laboratory whose id is laboratoryId
     *
     * @param model          ModelMap
     * @param studentPnc     pnc of the student
     * @param laboratoryName id of the laboratory
     * @param laboratoryId   name of the laboratory
     * @return String
     */
    @RequestMapping(value = "/student/{studentPnc}/grades/{laboratoryName}/{laboratoryId}", method = RequestMethod.GET)
    public String getStudentGrades(ModelMap model, @PathVariable String studentPnc, @PathVariable String laboratoryName,
        @PathVariable int laboratoryId) {
        LOGGER.info("Enter method");
        WelcomeUserDto welcomeUserDto = getPrincipal();
        model.addAttribute("welcomeUserDto", welcomeUserDto);

        List<GradeDto> gradeDtos = studentService.getStudentGradesByLaboratory(studentPnc, laboratoryId);
        model.addAttribute("gradeDtos", gradeDtos);

        model.addAttribute("laboratoryName", laboratoryName);

        return "student/studentGrades";
    }

    private WelcomeUserDto getPrincipal() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        WelcomeUserDto welcomeUserDto = new WelcomeUserDto();

        if (principal instanceof UserDetails) {
            try {
                welcomeUserDto = userService.getWelcomeUser(((UserDetails) principal).getUsername());
            } catch (ServiceEntityNotFoundException ignored) {
            }
        }

        return welcomeUserDto;
    }
}
