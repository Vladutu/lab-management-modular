package com.iquestint.controller;

import com.iquestint.dto.*;
import com.iquestint.exception.ServiceEntityAlreadyExistsException;
import com.iquestint.exception.ServiceEntityNotFoundException;
import com.iquestint.service.AdministrationFormService;
import com.iquestint.service.AdministrationLaboratoryService;
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
 * This class is a controller used for operations on Laboratory objects.
 *
 * @author Georgian Vladutu
 */
@Controller
@RequestMapping("/")
public class AdministrationLaboratoriesController {

    @Autowired
    private AdministrationLaboratoryService administrationLaboratoryService;

    @Autowired
    private AdministrationFormService administrationFormService;

    /**
     * Returns laboratory categories, ordered by section, year and semester
     *
     * @param model ModelMap
     * @return String
     */
    @RequestMapping(value = "/admin/laboratories", method = RequestMethod.GET)
    public String getAllLaboratories(ModelMap model) {
        FormLaboratoryShowDto formLaboratoryShowDto = administrationFormService.getFormLaboratoryShowDto();

        model.addAttribute("formLaboratoryShowDto", formLaboratoryShowDto);

        return "listLaboratoryCategories";
    }

    /**
     * Returns all existing laboratory models which have the same section, year and semester as method parameters.
     *
     * @param section  section
     * @param year     year
     * @param semester semester
     * @param model    ModelMap
     * @return String
     */
    @RequestMapping(value = "/admin/laboratories/{section}/{year}/{semester}", method = RequestMethod.GET)
    public String getLaboratories(@PathVariable String section, @PathVariable int year, @PathVariable int semester,
        ModelMap model) {
        List<LaboratoryDto> laboratoryDtos = administrationLaboratoryService.getLaboratories(new SectionDto(section),
            new YearDto(year), new SemesterDto(semester));

        model.addAttribute("section", section);
        model.addAttribute("year", year);
        model.addAttribute("semester", semester);
        model.addAttribute("laboratoryDtos", laboratoryDtos);

        return "listLaboratories";
    }

    /**
     * Deletes the laboratory whose id is laboratoryId. If the laboratory does not exists it will return an error message.
     *
     * @param section            section
     * @param year               year
     * @param semester           semester
     * @param laboratoryId       laboratoryId
     * @param model              ModelMap
     * @param redirectAttributes RedirectAttributes
     * @return String
     */
    @RequestMapping(value = "/admin/laboratories/{section}/{year}/{semester}/delete/{laboratoryId}", method = RequestMethod.GET)
    public String deleteLaboratory(@PathVariable String section, @PathVariable int year, @PathVariable int semester,
        @PathVariable int laboratoryId, ModelMap model,
        RedirectAttributes redirectAttributes) {
        try {
            administrationLaboratoryService.deleteLaboratory(laboratoryId);
        } catch (ServiceEntityNotFoundException e) {
            redirectAttributes.addFlashAttribute("errorMessage", "The laboratory does not exists or no longer exists");
        }

        return "redirect:/admin/laboratories/" + section + "/" + year + "/" + semester;
    }

    /**
     * Returns a form to add a new laboratory.
     *
     * @param section  section
     * @param year     year
     * @param semester semester
     * @param model    ModelMap
     * @return String
     */
    @RequestMapping(value = "/admin/laboratories/{section}/{year}/{semester}/new", method = RequestMethod.GET)
    public String newLaboratory(@PathVariable String section, @PathVariable int year, @PathVariable int semester,
        ModelMap model) {
        LaboratoryDto laboratoryDto = new LaboratoryDto();
        FormLaboratoryCreateDto formLaboratoryCreateDto = administrationFormService.getFormLaboratoryCreateDto();

        model.addAttribute("laboratoryDto", laboratoryDto);
        model.addAttribute("section", section);
        model.addAttribute("year", year);
        model.addAttribute("semester", semester);
        model.addAttribute("formLaboratoryCreateDto", formLaboratoryCreateDto);

        return "createLaboratory";
    }

    /**
     * This method checks if all the laboratory's fields are valid and then saves it. If at least one of the fields are not valid
     * the page returned will be the same.
     *
     * @param laboratoryDto      laboratoryDto
     * @param bindingResult      bindingResult
     * @param model              ModelMap
     * @param section            section
     * @param year               year
     * @param semester           semester
     * @param redirectAttributes RedirectAttributes
     * @return String
     */
    @RequestMapping(value = "/admin/laboratories/{section}/{year}/{semester}/new", method = RequestMethod.POST)
    public String saveLaboratory(@Valid LaboratoryDto laboratoryDto, BindingResult bindingResult, ModelMap model,
        @PathVariable String section, @PathVariable int year, @PathVariable int semester,
        RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            FormLaboratoryCreateDto formLaboratoryCreateDto = administrationFormService.getFormLaboratoryCreateDto();

            model.addAttribute("formLaboratoryCreateDto", formLaboratoryCreateDto);

            return "createLaboratory";
        }

        try {
            administrationLaboratoryService.saveLaboratory(laboratoryDto);
        } catch (ServiceEntityNotFoundException ignored) {
        } catch (ServiceEntityAlreadyExistsException e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Laboratory already exists");

            return "redirect:/admin/laboratories/" + section + "/" + year + "/" + semester;
        }

        return "redirect:/admin/laboratories/" + section + "/" + year + "/" + semester;
    }

    /**
     * Returns a form to update a laboratory whose id is laboratoryId.
     *
     * @param section            section
     * @param year               year
     * @param semester           semester
     * @param laboratoryId       laboratoryId
     * @param model              ModelMap
     * @param redirectAttributes RedirectAttributes
     * @return String
     */
    @RequestMapping(value = "/admin/laboratories/{section}/{year}/{semester}/edit/{laboratoryId}", method = RequestMethod.GET)
    public String editStudent(@PathVariable String section, @PathVariable int year, @PathVariable int semester,
        @PathVariable int laboratoryId, ModelMap model, RedirectAttributes redirectAttributes) {
        try {
            LaboratoryDto laboratoryDto = administrationLaboratoryService.getLaboratoryById(laboratoryId);
            FormLaboratoryCreateDto formLaboratoryCreateDto = administrationFormService.getFormLaboratoryCreateDto();

            model.addAttribute("formLaboratoryCreateDto", formLaboratoryCreateDto);
            model.addAttribute("laboratoryDto", laboratoryDto);

            return "updateLaboratory";
        } catch (ServiceEntityNotFoundException e) {
            redirectAttributes.addFlashAttribute("errorMessage", "The laboratory does not exists or no longer exists");

            return "redirect:/admin/laboratories/" + section + "/" + year + "/" + semester;
        }
    }

    /**
     * Checks if the laboratory model is valid, then updates it. If the laboratory model is not valid the same page will be returned.
     *
     * @param laboratoryDto      laboratoryDto
     * @param bindingResult      BindingResult
     * @param section            section
     * @param year               year
     * @param semester           semester
     * @param laboratoryId       laboratoryId
     * @param model              ModelMap
     * @param redirectAttributes RedirectAttributes
     * @return String
     */
    @RequestMapping(value = "/admin/laboratories/{section}/{year}/{semester}/edit/{laboratoryId}", method = RequestMethod.POST)
    public String updateLaboratory(@Valid LaboratoryDto laboratoryDto, BindingResult bindingResult,
        @PathVariable String section, @PathVariable int year, @PathVariable int semester,
        @PathVariable int laboratoryId, ModelMap model,
        RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            FormLaboratoryCreateDto formLaboratoryCreateDto = administrationFormService.getFormLaboratoryCreateDto();
            model.addAttribute("formLaboratoryCreateDto", formLaboratoryCreateDto);

            return "updateLaboratory";
        }

        try {
            administrationLaboratoryService.updateLaboratory(laboratoryDto);

            return "redirect:/admin/laboratories/" + section + "/" + year + "/" + semester;
        } catch (ServiceEntityNotFoundException e) {
            redirectAttributes.addFlashAttribute("errorMessage", "The laboratory does not exists or no longer exists");

            return "redirect:/admin/laboratories/" + section + "/" + year + "/" + semester;
        }
    }

}
