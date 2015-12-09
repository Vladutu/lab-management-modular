package com.iquestint.controller;

import com.iquestint.dto.*;
import com.iquestint.exception.ServiceEntityAlreadyExistsException;
import com.iquestint.exception.ServiceEntityNotFoundException;
import com.iquestint.exception.ServiceInvalidSemesterException;
import com.iquestint.service.ProfessorService;
import com.iquestint.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author vladu
 */
@Controller
@RequestMapping("/")
public class ProfessorsController {

    @Autowired
    private UserService userService;

    @Autowired
    private ProfessorService professorService;

    @RequestMapping(value = "/professor/home", method = RequestMethod.GET)
    public String getHome(ModelMap model) {
        WelcomeUserDto welcomeUserDto = getPrincipal();

        model.addAttribute("welcomeUserDto", welcomeUserDto);

        return "professorHome";
    }

    @RequestMapping(value = "/professor/currentLaboratory", method = RequestMethod.GET)
    public String getCurrentLaboratory(ModelMap model, RedirectAttributes redirectAttributes) {
        WelcomeUserDto welcomeUserDto = getPrincipal();
        FormStudentsWithGradeAndAttendanceDto formStudentsWithGradeAndAttendanceDto = new FormStudentsWithGradeAndAttendanceDto();

        model.addAttribute("welcomeUserDto", welcomeUserDto);
        model.addAttribute("formStudentsWithGradeAndAttendanceDto", formStudentsWithGradeAndAttendanceDto);

        try {
            LaboratoryWithStudentsDto laboratoryWithStudentsDto = professorService.getCurrentLaboratory(
                welcomeUserDto.getPnc(), LocalDateTime.now());
            model.addAttribute("laboratoryWithStudentsDto", laboratoryWithStudentsDto);

            return "currentLaboratory";

        } catch (ServiceInvalidSemesterException e) {
            redirectAttributes.addFlashAttribute("errorMessage",
                "Your system current date does not belong to a university semester");

            return "redirect:/professor/home";

        } catch (ServiceEntityNotFoundException e) {
            redirectAttributes.addFlashAttribute("errorMessage", "You don't have any laboratory now");

            return "redirect:/professor/home";
        }

    }

    @RequestMapping(value = "/professor/currentLaboratory", method = RequestMethod.POST)
    public String insertStudentsGradesAndAttendances(@Valid FormStudentsWithGradeAndAttendanceDto formStudents,
        BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "/professor/currentLaboratory";
        }

        for (StudentWithGradeAndAttendanceDto student : formStudents.getStudentsWithGradeAndAttendance()) {
            try {
                if (student.getAttendance()) {
                    professorService.saveStudentGrade(formStudents.getLaboratoryId(), student.getPnc(),
                        new GradeDto(student.getGrade(), LocalDate.now()));

                    professorService.saveStudentAttendance(formStudents.getLaboratoryId(), student.getPnc(),
                        new AttendanceDto(LocalDate.now()));
                }
            } catch (ServiceEntityAlreadyExistsException e) {
                redirectAttributes.addFlashAttribute("errorMessage",
                    "You already submitted grades and attendance for this laboratory today");

                return "redirect:/professor/home";
            }

        }

        return "redirect:/professor/home";
    }

    @RequestMapping(value = "/professor/laboratories", method = RequestMethod.GET)
    public String getProfessorLaboratories(ModelMap model) {
        WelcomeUserDto welcomeUserDto = getPrincipal();
        List<LaboratoryDto> laboratoryDtos = professorService.getLaboratories(welcomeUserDto.getPnc());

        model.addAttribute("welcomeUserDto", welcomeUserDto);
        model.addAttribute("laboratoryDtos", laboratoryDtos);

        return "listProfessorLaboratories";
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
