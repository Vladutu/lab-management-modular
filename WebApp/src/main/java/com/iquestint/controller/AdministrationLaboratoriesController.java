package com.iquestint.controller;

import com.iquestint.dto.*;
import com.iquestint.exception.ServiceEntityNotFoundException;
import com.iquestint.service.AdministrationFormService;
import com.iquestint.service.LaboratoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

/**
 * @author vladu
 */
@Controller
@RequestMapping("/")
public class AdministrationLaboratoriesController {

    @Autowired
    private LaboratoryService laboratoryService;

    @Autowired
    private AdministrationFormService administrationFormService;

    @RequestMapping(value = "/admin/laboratories", method = RequestMethod.GET)
    public String getAllLaboratories(ModelMap model) {
        FormLaboratoryShowDto formLaboratoryShowDto = administrationFormService.getFormLaboratoryShowDto();

        model.addAttribute("formLaboratoryShowDto", formLaboratoryShowDto);

        return "listLaboratoryCategories";
    }

    @RequestMapping(value = "/admin/laboratories/{section}/{year}/{semester}", method = RequestMethod.GET)
    public String getLaboratories(@PathVariable String section, @PathVariable int year, @PathVariable int semester,
        ModelMap model) {
        List<LaboratoryDto> laboratoryDtos = laboratoryService.getLaboratories(new SectionDto(section),
            new YearDto(year), new SemesterDto(semester));

        model.addAttribute("section", section);
        model.addAttribute("year", year);
        model.addAttribute("semester", semester);
        model.addAttribute("laboratoryDtos", laboratoryDtos);

        return "listLaboratories";
    }

    @RequestMapping(value = "/admin/laboratories/{section}/{year}/{semester}/delete/{laboratoryId}", method = RequestMethod.GET)
    public String deleteLaboratory(@PathVariable String section, @PathVariable int year, @PathVariable int semester,
        @PathVariable int laboratoryId, ModelMap model,
        RedirectAttributes redirectAttributes) {
        try {
            laboratoryService.deleteLaboratory(laboratoryId);
        } catch (ServiceEntityNotFoundException e) {
            redirectAttributes.addFlashAttribute("errorMessage", "The laboratory does not exists or no longer exists");
        }

        return "redirect:/admin/laboratories/" + section + "/" + year + "/" + semester;
    }

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

}
