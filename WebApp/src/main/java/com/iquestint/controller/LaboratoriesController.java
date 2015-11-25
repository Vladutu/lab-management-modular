package com.iquestint.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author vladu
 */
@Controller
@RequestMapping("/")
public class LaboratoriesController {

//    @Autowired
//    private LaboratoryService laboratoryService;
//
//    @Autowired
//    private SectionService sectionService;
//
//    @Autowired
//    private YearService yearService;
//
//    @Autowired
//    private SemesterService semesterService;
//
//    @Autowired
//    private DayService dayService;
//
//    @Autowired
//    private HourService hourService;
//
//    @Autowired
//    private WeeklyOccurrenceService weeklyOccurrenceService;
//
//    @Autowired
//    private RoomService roomService;
//
//    @Autowired
//    private GroupService groupService;
//
//    @Autowired
//    private SubgroupService subgroupService;
//
//    @Autowired
//    private ProfessorService professorService;
//
//    @Autowired
//    private LaboratoryMapper laboratoryMapper;
//
//    @Autowired
//    private SectionMapper sectionMapper;
//
//    @Autowired
//    private YearMapper yearMapper;
//
//    @Autowired
//    private SemesterMapper semesterMapper;
//
//    @Autowired
//    private HourMapper hourMapper;
//
//    @Autowired
//    private GroupMapper groupMapper;
//
//    @Autowired
//    private SubgroupMapper subgroupMapper;
//
//    @Autowired
//    private RoomMapper roomMapper;
//
//    @Autowired
//    private DayMapper dayMapper;
//
//    @Autowired
//    private WeeklyOccurrenceMapper weeklyOccurrenceMapper;
//
//    @Autowired
//    private SimplifiedProfessorMapper simplifiedProfessorMapper;
//
//    @RequestMapping(value = "/admin/laboratories", method = RequestMethod.GET)
//    public String getAllLaboratories(ModelMap model) {
//        initializeDtoListsForShowingLaboratories(model);
//
//        return "listLaboratoryCategories";
//    }
//
//    @RequestMapping(value = "/admin/laboratories/{section}/{year}/{semester}", method = RequestMethod.GET)
//    public String getLaboratories(@PathVariable String section, @PathVariable int year, @PathVariable int semester,
//        ModelMap model) {
//        List<Laboratory> laboratories = laboratoryService.getLaboratories(new Section(section), new Year(year),
//            new Semester(semester));
//        List<LaboratoryDto> laboratoryDtos = laboratoryMapper.mapList(laboratories);
//
//        model.addAttribute("section", section);
//        model.addAttribute("year", year);
//        model.addAttribute("semester", semester);
//        model.addAttribute("laboratoryDtos", laboratoryDtos);
//
//        return "listLaboratories";
//    }
//
//    @RequestMapping(value = "/admin/laboratories/{section}/{year}/{semester}/delete/{laboratoryId}", method = RequestMethod.GET)
//    public String deleteLaboratory(@PathVariable String section, @PathVariable int year, @PathVariable int semester,
//        @PathVariable int laboratoryId, ModelMap model,
//        RedirectAttributes redirectAttributes) {
//        try {
//            laboratoryService.deleteLaboratory(laboratoryId);
//        }
//        catch (ServiceEntityNotFoundException e) {
//            redirectAttributes.addFlashAttribute("errorMessage", "The laboratory does not exists or no longer exists");
//        }
//
//        return "redirect:/admin/laboratories/" + section + "/" + year + "/" + semester;
//    }
//
//    @RequestMapping(value = "/admin/laboratories/{section}/{year}/{semester}/new", method = RequestMethod.GET)
//    public String newLaboratory(@PathVariable String section, @PathVariable int year, @PathVariable int semester,
//        ModelMap model) {
//        LaboratoryDto laboratoryDto = new LaboratoryDto();
//        model.addAttribute("laboratoryDto", laboratoryDto);
//        model.addAttribute("section", section);
//        model.addAttribute("year", year);
//        model.addAttribute("semester", semester);
//
//        initializeDtoListsForCreatingLaboratory(model);
//
//        return "createLaboratory";
//    }
//
//    private void initializeDtoListsForShowingLaboratories(ModelMap model) {
//        List<Section> sections = sectionService.getAllSections();
//        List<Year> years = yearService.getAllYears();
//        List<Semester> semesters = semesterService.getAllSemesters();
//
//        List<SectionDto> sectionDtos = sectionMapper.mapList(sections);
//        List<YearDto> yearDtos = yearMapper.mapList(years);
//        List<SemesterDto> semesterDtos = semesterMapper.mapList(semesters);
//
//        model.addAttribute("sectionDtos", sectionDtos);
//        model.addAttribute("yearDtos", yearDtos);
//        model.addAttribute("semesterDtos", semesterDtos);
//    }
//
//    private void initializeDtoListsForCreatingLaboratory(ModelMap model) {
//        List<Hour> hours = hourService.getAllHours();
//        List<Day> days = dayService.getAllDays();
//        List<Room> rooms = roomService.getAllRooms();
//        List<Group> groups = groupService.getAllGroups();
//        List<Subgroup> subgroups = subgroupService.getAllSubgroups();
//        List<WeeklyOccurrence> weeklyOccurrences = weeklyOccurrenceService.getAllWeeklyOccurrence();
//        List<Professor> professors = professorService.getAllProfessors();
//
//        List<HourDto> hourDtos = hourMapper.mapList(hours);
//        List<DayDto> dayDtos = dayMapper.mapList(days);
//        List<RoomDto> roomDtos = roomMapper.mapList(rooms);
//        List<GroupDto> groupDtos = groupMapper.mapList(groups);
//        List<SubgroupDto> subgroupDtos = subgroupMapper.mapList(subgroups);
//        List<WeeklyOccurrenceDto> weeklyOccurrenceDtos = weeklyOccurrenceMapper.mapList(weeklyOccurrences);
//        List<SimplifiedProfessorDto> professorDtos = simplifiedProfessorMapper.mapList(professors);
//
//        model.addAttribute("hourDtos", hourDtos);
//        model.addAttribute("dayDtos", dayDtos);
//        model.addAttribute("roomDtos", roomDtos);
//        model.addAttribute("groupDtos", groupDtos);
//        model.addAttribute("subgroupDtos", subgroupDtos);
//        model.addAttribute("weeklyOccurrenceDtos", weeklyOccurrenceDtos);
//        model.addAttribute("professorDtos", professorDtos);
//
//    }

}
