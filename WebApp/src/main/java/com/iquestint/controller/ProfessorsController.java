package com.iquestint.controller;

import com.iquestint.dto.ProfessorLaboratoryDto;
import com.iquestint.dto.WelcomeUserDto;
import com.iquestint.exception.ServiceEntityNotFoundException;
import com.iquestint.exception.ServiceInvalidSemesterException;
import com.iquestint.service.ProfessorService;
import com.iquestint.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDateTime;

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
        model.addAttribute("welcomeUserDto", welcomeUserDto);
        try {
            ProfessorLaboratoryDto professorLaboratoryDto = professorService.getCurrentLaboratory(
                welcomeUserDto.getPnc(), LocalDateTime.now());
            model.addAttribute("professorLaboratoryDto", professorLaboratoryDto);

            return "currentLaboratory";

        } catch (ServiceInvalidSemesterException e) {
            redirectAttributes.addFlashAttribute("errorMessage",
                "Your sistem current date does not belong to a university semester");

            return "redirect:/professor/home";

        } catch (ServiceEntityNotFoundException e) {
            redirectAttributes.addFlashAttribute("errorMessage",
                "You don't have any laboratory now");

            return "redirect:/professor/home";
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
