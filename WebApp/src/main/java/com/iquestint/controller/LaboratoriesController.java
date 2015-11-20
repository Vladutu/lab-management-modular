package com.iquestint.controller;

import com.iquestint.dto.SectionDto;
import com.iquestint.dto.SemesterDto;
import com.iquestint.dto.YearDto;
import com.iquestint.model.Laboratory;
import com.iquestint.model.Section;
import com.iquestint.model.Semester;
import com.iquestint.model.Year;
import com.iquestint.service.LaboratoryService;
import com.iquestint.service.SectionService;
import com.iquestint.service.SemesterService;
import com.iquestint.service.YearService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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

        model.addAttribute("laboratories", laboratories);

        return "listLaboratories";
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
