package com.iquestint.controller;

import com.iquestint.exception.ServiceEntityAlreadyExistsException;
import com.iquestint.exception.ServiceEntityNotFoundException;
import com.iquestint.dto.ProfessorDto;
import com.iquestint.service.AdministrationProfessorService;
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
 * This class is a controller used for operations on Professor objects.
 *
 * @author Georgian Vladutu
 */
@Controller
@RequestMapping("/")
public class AdministrationProfessorsController {

    @Autowired
    private AdministrationProfessorService administrationProfessorService;

    /**
     * Returns all existing professors.
     *
     * @param model ModelMap
     * @return String
     */
    @RequestMapping(value = "/admin/professors", method = RequestMethod.GET)
    public String getProfessors(ModelMap model) {
        List<ProfessorDto> professorDtos = administrationProfessorService.getAllProfessors();

        model.addAttribute("professorDtos", professorDtos);

        return "listProfessors";
    }

    /**
     * Returns a form to add a new professor.
     *
     * @param model ModelMap
     * @return String
     */
    @RequestMapping(value = "/admin/professors/new", method = RequestMethod.GET)
    public String newProfessor(ModelMap model) {
        ProfessorDto professorDto = new ProfessorDto();
        model.addAttribute("professorDto", professorDto);

        return "createProfessor";
    }

    /**
     * This method checks if all the professor's fields are valid and saves it. If at least one of the fields are not valid
     * the page returned will be the same.
     *
     * @param professorDto       professor model
     * @param bindingResult      BindingResult
     * @param model              ModelMap
     * @param redirectAttributes RequestAttributes
     * @return String
     */
    @RequestMapping(value = "/admin/professors/new", method = RequestMethod.POST)
    public String saveProfessor(@Valid ProfessorDto professorDto, BindingResult bindingResult, ModelMap model,
        RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "createProfessor";
        }

        try {
            administrationProfessorService.saveProfessor(professorDto);
        } catch (ServiceEntityAlreadyExistsException e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Professor already exists");

            return "redirect:/admin/professors/new";
        }

        return "redirect:/admin/professors";

    }

    /**
     * Deletes the professor whose pnc is professorPnc. If the professor doesn't exists it will return an error message.
     *
     * @param professorPnc       the pnc of the student
     * @param model              ModelMap
     * @param redirectAttributes RedirectAttributes
     * @return String
     */
    @RequestMapping(value = "/admin/professors/delete/{professorPnc}", method = RequestMethod.GET)
    public String deleteProfessor(@PathVariable String professorPnc, ModelMap model,
        RedirectAttributes redirectAttributes) {
        try {
            administrationProfessorService.deleteProfessor(professorPnc);
        } catch (ServiceEntityNotFoundException e) {
            redirectAttributes.addFlashAttribute("errorMessage", "The professor does not exists or no longer exists");
        }

        return "redirect:/admin/professors";
    }

    /**
     * Returns a form to update a professor whose pnc is professorPnc.
     *
     * @param professorPnc       the pnc of the professor
     * @param model              ModelMap
     * @param redirectAttributes RedirectAttributes
     * @return String
     */
    @RequestMapping(value = "/admin/professors/edit/{professorPnc}", method = RequestMethod.GET)
    public String editProfessor(@PathVariable String professorPnc, ModelMap model,
        RedirectAttributes redirectAttributes) {
        try {
            ProfessorDto professorDto = administrationProfessorService.getProfessorByPnc(professorPnc);
            model.addAttribute("professorDto", professorDto);

            return "updateProfessor";
        } catch (ServiceEntityNotFoundException e) {
            redirectAttributes.addFlashAttribute("errorMessage", "The professor does not exists or no longer exists");

            return "redirect:/admin/professors";
        }
    }

    /**
     * Checks if the professor model is valid, then updates it. If the professor model is not valid the same page will be returned.
     *
     * @param professorDto       the professor model
     * @param bindingResult      BindingResult
     * @param model              ModelMap
     * @param professorPnc       the pnc of the professor
     * @param redirectAttributes RedirectAttributes
     * @return String
     */
    @RequestMapping(value = "/admin/professors/edit/{professorPnc}", method = RequestMethod.POST)
    public String updateProfessor(@Valid ProfessorDto professorDto, BindingResult bindingResult, ModelMap model,
        @PathVariable String professorPnc, RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            return "updateProfessor";
        }

        try {
            administrationProfessorService.updateProfessor(professorDto);

            return "redirect:/admin/professors";
        } catch (ServiceEntityNotFoundException e) {
            redirectAttributes.addFlashAttribute("errorMessage", "The professor does not exists or no longer exists");

            return "redirect:/admin/professors";
        }
    }

}
