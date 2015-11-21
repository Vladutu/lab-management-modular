package com.iquestint.controller;

import com.iquestint.dto.LaboratoryDto;
import com.iquestint.dto.SectionDto;
import com.iquestint.dto.SemesterDto;
import com.iquestint.dto.YearDto;
import com.iquestint.exception.ServiceEntityNotFoundException;
import com.iquestint.model.Laboratory;
import com.iquestint.model.Section;
import com.iquestint.model.Semester;
import com.iquestint.model.Year;
import com.iquestint.service.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

/**
 * @author vladu
 */
@Controller
@RequestMapping("/")
public class LaboratoriesController {

    @Autowired
    private LaboratoryService laboratoryService;

    @Autowired
    private SectionService sectionService;

    @Autowired
    private YearService yearService;

    @Autowired
    private SemesterService semesterService;

    @Autowired
    private DayService dayService;

    @Autowired
    private HourService hourService;

    @Autowired
    private WeeklyOccurrenceService weeklyOccurrenceService;

    @Autowired
    private RoomService roomService;

    @Autowired
    private GroupService groupService;

    @Autowired
    private SubgroupService subgroupService;

    @Autowired
    ModelMapper modelMapper;

    @RequestMapping(value = "/admin/laboratories", method = RequestMethod.GET)
    public String getAllLaboratories(ModelMap model) {
        initializeDtoLists(model);

        return "listLaboratoryCategories";
    }

    @RequestMapping(value = "/admin/laboratories/{section}/{year}/{semester}", method = RequestMethod.GET)
    public String getLaboratories(@PathVariable String section, @PathVariable int year, @PathVariable int semester,
        ModelMap model) {

        List<Laboratory> laboratories = laboratoryService.getLaboratories(new Section(section), new Year(year),
            new Semester(semester));
        List<LaboratoryDto> laboratoryDtos = new ArrayList<>();

        for (Laboratory laboratory : laboratories) {
            laboratoryDtos.add(modelMapper.map(laboratory, LaboratoryDto.class));
        }

        model.addAttribute("laboratoryDtos", laboratoryDtos);

        return "listLaboratories";
    }

    @RequestMapping(value = "/admin/laboratories/{section}/{year}/{semester}/delete/{laboratoryId}", method = RequestMethod.GET)
    public String deleteLaboratory(@PathVariable String section, @PathVariable int year, @PathVariable int semester,
        @PathVariable int laboratoryId, ModelMap model,
        RedirectAttributes redirectAttributes) {
        try {
            laboratoryService.deleteLaboratory(laboratoryId);
        }
        catch (ServiceEntityNotFoundException e) {
            redirectAttributes.addFlashAttribute("errorMessage", "The laboratory does not exists or no longer exists");
        }

        return "redirect:/admin/laboratories/" + section + "/" + year + "/" + semester;
    }

    @RequestMapping(value = "/admin/laboratories/{section}/{year}/{semester}/new", method = RequestMethod.GET)
    public String newLaboratory(@PathVariable String section, @PathVariable int year, @PathVariable int semester,
        ModelMap model) {
        LaboratoryDto laboratoryDto = new LaboratoryDto();
        model.addAttribute("laboratoryDto", laboratoryDto);

        return "createLaboratory";
    }

    private void initializeDtoLists(ModelMap model) {
        List<Section> sections = sectionService.getAllSections();
        List<Year> years = yearService.getAllYears();
        List<Semester> semesters = semesterService.getAllSemesters();

        List<SectionDto> sectionDtos = new ArrayList<>();
        List<YearDto> yearDtos = new ArrayList<>();
        List<SemesterDto> semesterDtos = new ArrayList<>();

        for (Section section : sections) {
            sectionDtos.add(modelMapper.map(section, SectionDto.class));
        }
        for (Year year : years) {
            yearDtos.add(modelMapper.map(year, YearDto.class));
        }
        for (Semester semester : semesters) {
            semesterDtos.add(modelMapper.map(semester, SemesterDto.class));
        }

        model.addAttribute("sectionDtos", sectionDtos);
        model.addAttribute("yearDtos", yearDtos);
        model.addAttribute("semesterDtos", semesterDtos);
    }

}
