package com.iquestint.controller;

import com.iquestint.dto.*;
import com.iquestint.exception.ServiceEntityAlreadyExistsException;
import com.iquestint.exception.ServiceEntityNotFoundException;
import com.iquestint.exception.ServiceIOException;
import com.iquestint.exception.ServiceInvalidSemesterException;
import com.iquestint.service.DocumentService;
import com.iquestint.service.NoteService;
import com.iquestint.service.ProfessorService;
import com.iquestint.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.List;

/**
 * This class is a controller used by a professor user.
 *
 * @author Georgian Vladutu
 */
@Controller
@RequestMapping("/")
public class ProfessorsController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProfessorsController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private ProfessorService professorService;

    @Autowired
    private DocumentService documentService;

    @Autowired
    private NoteService noteService;

    /**
     * Returns the professor's home page.
     *
     * @param model ModelMap
     * @return String
     */
    @RequestMapping(value = "/professor/home", method = RequestMethod.GET)
    public String getHome(ModelMap model) {
        LOGGER.info("Enter method");
        WelcomeUserDto welcomeUserDto = getPrincipal();

        model.addAttribute("welcomeUserDto", welcomeUserDto);

        return "professor/professorHome";
    }

    /**
     * Returns a view with the professor current laboratory given the current date and time.
     *
     * @param model              ModelMap
     * @param redirectAttributes RedirectAttributes
     * @return String
     */
    @RequestMapping(value = "/professor/currentLaboratory", method = RequestMethod.GET)
    public String getCurrentLaboratory(ModelMap model, RedirectAttributes redirectAttributes) {
        LOGGER.info("Enter method");
        WelcomeUserDto welcomeUserDto = getPrincipal();
        FormStudentsWithGradeAndAttendanceDto formStudentsWithGradeAndAttendanceDto = new FormStudentsWithGradeAndAttendanceDto();

        model.addAttribute("welcomeUserDto", welcomeUserDto);
        model.addAttribute("formStudentsWithGradeAndAttendanceDto", formStudentsWithGradeAndAttendanceDto);

        try {
            LaboratoryWithStudentsDto laboratoryWithStudentsDto = professorService.getCurrentLaboratory(
                welcomeUserDto.getPnc(), LocalDateTime.now());
            model.addAttribute("laboratoryWithStudentsDto", laboratoryWithStudentsDto);

            return "professor/currentLaboratory";

        } catch (ServiceInvalidSemesterException e) {
            redirectAttributes.addFlashAttribute("errorMessage",
                "Your system current date does not belong to a university semester");

            return "redirect:/professor/home";

        } catch (ServiceEntityNotFoundException e) {
            redirectAttributes.addFlashAttribute("errorMessage", "You don't have any laboratory now");

            return "redirect:/professor/home";
        }

    }

    /**
     * Saves the students grades and attendances.
     *
     * @param formStudents       FormStudentsWithGradeAndAttendanceDto
     * @param bindingResult      BindingResult
     * @param model              ModelMap
     * @param redirectAttributes RedirectAttributes
     * @return String
     */
    @RequestMapping(value = "/professor/currentLaboratory", method = RequestMethod.POST)
    public String insertStudentsGradesAndAttendances(@Valid FormStudentsWithGradeAndAttendanceDto formStudents,
        BindingResult bindingResult, ModelMap model, RedirectAttributes redirectAttributes) {
        LOGGER.info("Enter method");
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("errorMessage", "Every present student must have a grade");

            return "redirect:/professor/currentLaboratory";
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

    /**
     * Returns a view with all the laboratories that the professor teach.
     *
     * @param model ModelMap
     * @return String
     */
    @RequestMapping(value = "/professor/laboratories", method = RequestMethod.GET)
    public String getProfessorLaboratories(ModelMap model) {
        LOGGER.info("Enter method");
        WelcomeUserDto welcomeUserDto = getPrincipal();
        List<LaboratoryDto> laboratoryDtos = professorService.getLaboratories(welcomeUserDto.getPnc());

        model.addAttribute("welcomeUserDto", welcomeUserDto);
        model.addAttribute("laboratoryDtos", laboratoryDtos);

        return "professor/listProfessorLaboratories";
    }

    /**
     * Returns a view with the students and their grades of the laboratory and on the date specified by the request parameters.
     *
     * @param model ModelMap
     * @param id    id of the laboratory
     * @param date  date on which the laboratory took place
     * @return String
     */
    @RequestMapping(value = "/professor/laboratory", params = { "id", "date" })
    public String getStudentGrading(ModelMap model, @RequestParam(value = "id") int id,
        @RequestParam(value = "date") String date) {
        LOGGER.info("Enter method");
        LocalDate localDate;
        try {
            localDate = LocalDate.parse(date);
        } catch (DateTimeParseException e) {
            return "redirect:/professor/laboratories";
        }

        WelcomeUserDto welcomeUserDto = getPrincipal();
        List<StudentGradingDto> studentGradingDtos = professorService.getStudentsWithGradesByLaboratory(id, localDate);

        model.addAttribute("welcomeUserDto", welcomeUserDto);
        model.addAttribute("studentGradingDtos", studentGradingDtos);

        return "professor/studentsGradesByLaboratory";
    }

    /**
     * Returns the platforms which belong to the laboratory whose id is laboratoryId.
     *
     * @param model          ModelMap
     * @param laboratoryId   id of the laboratory
     * @param laboratoryName name of the laboratory
     * @return String
     */
    @RequestMapping(value = "/professor/laboratories/{laboratoryId}/{laboratoryName}/platform", method = RequestMethod.GET)
    public String getLaboratoryPlatform(ModelMap model, @PathVariable int laboratoryId,
        @PathVariable String laboratoryName) {
        LOGGER.info("Enter method");

        WelcomeUserDto welcomeUserDto = getPrincipal();
        model.addAttribute("welcomeUserDto", welcomeUserDto);

        List<DocumentDto> documentDtos = documentService.getDocumentsByLaboratory(laboratoryId);
        model.addAttribute("documentDtos", documentDtos);

        FileBucket fileBucket = new FileBucket();
        model.addAttribute("fileBucket", fileBucket);

        model.addAttribute("laboratoryName", laboratoryName);

        return "professor/platform";
    }

    /**
     * Saved a document which belong to the laboratory whose id is laboratoryId into the repository.
     *
     * @param fileBucket         document to be saved
     * @param bindingResult      BindingResult
     * @param model              ModelMap
     * @param laboratoryId       id of the laboratory
     * @param laboratoryName     name of the laboratory
     * @param redirectAttributes RedirectAttributes
     * @return String
     */
    @RequestMapping(value = "/professor/laboratories/{laboratoryId}/{laboratoryName}/platform", method = RequestMethod.POST)
    public String uploadDocument(@Valid FileBucket fileBucket, BindingResult bindingResult, ModelMap model,
        @PathVariable int laboratoryId, @PathVariable String laboratoryName, RedirectAttributes redirectAttributes) {
        LOGGER.info("Enter method");
        if (bindingResult.hasErrors()) {
            WelcomeUserDto welcomeUserDto = getPrincipal();
            model.addAttribute("welcomeUserDto", welcomeUserDto);

            List<DocumentDto> documentDtos = documentService.getDocumentsByLaboratory(laboratoryId);
            model.addAttribute("documentDtos", documentDtos);

            FileBucket bucket = new FileBucket();
            model.addAttribute("fileBucket", bucket);

            model.addAttribute("laboratoryName", laboratoryName);
            model.addAttribute("laboratoryId", laboratoryId);

            return "professor/platform";
        }

        try {
            documentService.saveDocument(fileBucket, laboratoryId);
        } catch (ServiceEntityAlreadyExistsException e) {
            redirectAttributes.addFlashAttribute("errorMessage", "File already exists");
        } catch (ServiceIOException ignored) {
        }

        return "redirect:/professor/laboratories/" + laboratoryId + "/" + laboratoryName + "/platform";
    }

    /**
     * Deletes the document whose id is document id.
     *
     * @param model              ModelMap
     * @param documentId         id of the document
     * @param laboratoryId       id of the laboratory
     * @param laboratoryName     name of the laboratory
     * @param redirectAttributes RedirectAttributes
     * @return String
     */
    @RequestMapping(value = "/professor/laboratories/{laboratoryId}/{laboratoryName}/platform/documents/{documentId}", method = RequestMethod.GET)
    public String deleteDocument(ModelMap model, @PathVariable int documentId, @PathVariable String laboratoryId,
        @PathVariable String laboratoryName, RedirectAttributes redirectAttributes) {
        LOGGER.info("Enter method");
        try {
            documentService.deleteDocument(documentId);
        } catch (ServiceEntityNotFoundException e) {
            redirectAttributes.addFlashAttribute("errorMessage", "File not found");
        }

        return "redirect:/professor/laboratories/" + laboratoryId + "/" + laboratoryName + "/platform";
    }

    /**
     * Returns the notes which belong to the laboratory whose id is laboratoryId.
     *
     * @param model          ModelMap
     * @param laboratoryId   id of the laboratory
     * @param laboratoryName name of the laboratory
     * @return String
     */
    @RequestMapping(value = "/professor/laboratories/{laboratoryId}/{laboratoryName}/note", method = RequestMethod.GET)
    public String getNotes(ModelMap model, @PathVariable int laboratoryId, @PathVariable String laboratoryName) {
        LOGGER.info("Enter method");

        WelcomeUserDto welcomeUserDto = getPrincipal();
        model.addAttribute("welcomeUserDto", welcomeUserDto);

        List<NoteDto> noteDtos = noteService.getNotesByLaboratory(laboratoryId);
        model.addAttribute("noteDtos", noteDtos);

        NoteDto noteDto = new NoteDto();
        model.addAttribute("noteDto", noteDto);

        model.addAttribute("laboratoryName", laboratoryName);
        model.addAttribute("laboratoryId", laboratoryId);

        return "professor/note";
    }

    /**
     * Saves the note which belong to the laboratory whose id is laboratoryId into the repository.
     *
     * @param noteDto            note to be saved
     * @param bindingResult      BindingResult
     * @param model              ModelMap
     * @param laboratoryId       id of the laboratory
     * @param laboratoryName     name of the laboratory
     * @param redirectAttributes RedirectAttributes
     * @return String
     */
    @RequestMapping(value = "/professor/laboratories/{laboratoryId}/{laboratoryName}/note", method = RequestMethod.POST)
    public String saveNote(@Valid NoteDto noteDto, BindingResult bindingResult, ModelMap model,
        @PathVariable int laboratoryId, @PathVariable String laboratoryName, RedirectAttributes redirectAttributes) {
        LOGGER.info("Enter method");
        WelcomeUserDto welcomeUserDto = getPrincipal();
        model.addAttribute("welcomeUserDto", welcomeUserDto);

        model.addAttribute("laboratoryName", laboratoryName);
        model.addAttribute("laboratoryId", laboratoryId);

        if (bindingResult.hasErrors()) {
            NoteDto newNoteDto = new NoteDto();
            model.addAttribute("noteDto", newNoteDto);

            return "/professor/laboratories/" + laboratoryId + "/" + laboratoryName + "/note";
        }

        try {
            noteService.saveNote(noteDto, laboratoryId);
        } catch (ServiceEntityAlreadyExistsException e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Note already exists");
        }

        return "redirect:/professor/laboratories/" + laboratoryId + "/" + laboratoryName + "/note";
    }

    /**
     * Deletes the note whose id is noteId from the repository.
     *
     * @param model              ModelMap
     * @param laboratoryId       id of the laboratory
     * @param laboratoryName     name of the laboratory
     * @param noteId             id of the note
     * @param redirectAttributes RedirectAttributes
     * @return String
     */
    @RequestMapping(value = "/professor/laboratories/{laboratoryId}/{laboratoryName}/note/{noteId}", method = RequestMethod.GET)
    public String deleteNote(ModelMap model, @PathVariable int laboratoryId, @PathVariable String laboratoryName,
        @PathVariable int noteId, RedirectAttributes redirectAttributes) {
        LOGGER.info("Enter method");
        try {
            noteService.deleteNode(noteId);
        } catch (ServiceEntityNotFoundException e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Note not found");
        }

        return "redirect:/professor/laboratories/" + laboratoryId + "/" + laboratoryName + "/note";
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
