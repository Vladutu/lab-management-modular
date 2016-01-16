package com.iquestint.controller;

import com.iquestint.dto.*;
import com.iquestint.exception.ServiceEntityNotFoundException;
import com.iquestint.service.DocumentService;
import com.iquestint.service.StudentService;
import com.iquestint.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
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

    @Autowired
    private DocumentService documentService;

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
     * Returns the student grades at the laboratory whose id is laboratoryId.
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

    /**
     * Returns the student attendances at the laboratory whose id is laboratoryId.
     *
     * @param model          ModelMap
     * @param studentPnc     pnc of the student
     * @param laboratoryName name of the laboratory
     * @param laboratoryId   id of the laboratory
     * @return String
     */
    @RequestMapping(value = "/student/{studentPnc}/attendances/{laboratoryName}/{laboratoryId}", method = RequestMethod.GET)
    public String getStudentAttendances(ModelMap model, @PathVariable String studentPnc,
        @PathVariable String laboratoryName, @PathVariable int laboratoryId) {
        LOGGER.info("Enter method");
        WelcomeUserDto welcomeUserDto = getPrincipal();
        model.addAttribute("welcomeUserDto", welcomeUserDto);

        List<AttendanceDto> attendanceDtos = studentService.getStudentAttendancesByLaboratory(studentPnc, laboratoryId);
        model.addAttribute("attendanceDtos", attendanceDtos);

        model.addAttribute("laboratoryName", laboratoryName);

        return "student/studentAttendances";
    }

    @RequestMapping(value = "/student/laboratory/{laboratoryName}/{laboratoryId}/platform", method = RequestMethod.GET)
    public String getPlatforms(ModelMap model, @PathVariable String laboratoryName, @PathVariable int laboratoryId) {
        LOGGER.info("Enter method");
        WelcomeUserDto welcomeUserDto = getPrincipal();
        model.addAttribute("welcomeUserDto", welcomeUserDto);
        model.addAttribute("laboratoryName", laboratoryName);
        model.addAttribute("laboratoryId", laboratoryId);

        List<DocumentDto> documentDtos = documentService.getDocumentsByLaboratory(laboratoryId);
        model.addAttribute("documentDtos", documentDtos);

        return "student/platform";
    }

    @RequestMapping(value = "/student/laboratory/{laboratoryName}/{laboratoryId}/platform/{documentId}")
    public String downloadPlatform(ModelMap model, @PathVariable String laboratoryName, @PathVariable int laboratoryId,
        @PathVariable int documentId, HttpServletResponse response, RedirectAttributes redirectAttributes)
        throws IOException {
        LOGGER.info("Enter method");
        WelcomeUserDto welcomeUserDto = getPrincipal();
        model.addAttribute("welcomeUserDto", welcomeUserDto);
        model.addAttribute("laboratoryName", laboratoryName);
        model.addAttribute("laboratoryId", laboratoryId);

        try {
            DocumentWithContentDto documentDto = documentService.getDocument(documentId);
            response.setContentType(documentDto.getType());
            response.setContentLength(documentDto.getContent().length);
            response.setHeader("Content-Disposition", "attachment; filename=\"" + documentDto.getName() + "\"");
            FileCopyUtils.copy(documentDto.getContent(), response.getOutputStream());

            return "redirect:/student/laboratory/" + laboratoryName + "/" + laboratoryId + "platform";
        } catch (ServiceEntityNotFoundException e) {
            redirectAttributes.addFlashAttribute("errorMessage", "File not found");

            return "redirect:/student/laboratory/" + laboratoryName + "/" + laboratoryId + "platform";
        }
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
