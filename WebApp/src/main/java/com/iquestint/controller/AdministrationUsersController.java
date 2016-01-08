package com.iquestint.controller;

import com.iquestint.dto.FormUserDto;
import com.iquestint.dto.UnregisteredUserDto;
import com.iquestint.dto.UserDto;
import com.iquestint.enums.State;
import com.iquestint.exception.ServiceEntityAlreadyExistsException;
import com.iquestint.exception.ServiceEntityNotFoundException;
import com.iquestint.jms.MessageSender;
import com.iquestint.jms.email.EmailRequest;
import com.iquestint.populator.EmailRequestPopulator;
import com.iquestint.service.AdministrationFormService;
import com.iquestint.service.AdministrationUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

/**
 * This class is a controller used for operations on User objects.
 *
 * @author Georgian Vladutu
 */
@Controller
@RequestMapping("/")
public class AdministrationUsersController {

    @Autowired
    private AdministrationUserService administrationUserService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AdministrationFormService administrationFormService;

    @Autowired
    private MessageSender messageSender;

    @Autowired
    private EmailRequestPopulator emailRequestPopulator;

    /**
     * Returns all existing users.
     *
     * @param model ModelMap
     * @return String
     */
    @RequestMapping(value = "/admin/users", method = RequestMethod.GET)
    public String getUsers(ModelMap model) {
        List<UserDto> userDtos = administrationUserService.getAllUsers();

        model.addAttribute("userDtos", userDtos);

        return "listUsers";
    }

    /**
     * Returns a form to add a new user.
     *
     * @param model ModelMap
     * @return String
     */
    @RequestMapping(value = "/admin/users/new", method = RequestMethod.GET)
    public String newUser(ModelMap model) {
        FormUserDto formUserDto = administrationFormService.getFormUser();
        UserDto userDto = new UserDto();

        model.addAttribute("formUserDto", formUserDto);
        model.addAttribute("userDto", userDto);

        return "createUser";
    }

    /**
     * This method checks if all the user's fields are valid and saves it. If at least one of the fields are not valid
     * the page returned will be the same.
     *
     * @param userDto            user model
     * @param bindingResult      BindingResult
     * @param model              ModelMap
     * @param redirectAttributes RequestAttributes
     * @return String
     */
    @RequestMapping(value = "/admin/users/new", method = RequestMethod.POST)
    public String saveUser(@Valid UserDto userDto, BindingResult bindingResult, ModelMap model,
        RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            FormUserDto formUserDto = administrationFormService.getFormUser();

            model.addAttribute("formUserDto", formUserDto);

            return "createUser";
        }

        userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
        userDto.setUserState(State.ACTIVE.getState());

        try {
            administrationUserService.saveUser(userDto);

            EmailRequest emailRequest = emailRequestPopulator.populate(userDto);

            messageSender.sendMessage(emailRequest);

        } catch (ServiceEntityAlreadyExistsException e) {
            redirectAttributes.addFlashAttribute("errorMessage", "User already exists");

            return "redirect:/admin/users/new";
        } catch (ServiceEntityNotFoundException ignored) {
        }

        return "redirect:/admin/users";

    }

    /**
     * Deletes the user whose pnc is pnc. If the user doesn't exists it will return an error message.
     *
     * @param pnc                the pnc of the user
     * @param model              ModelMap
     * @param redirectAttributes RedirectAttributes
     * @return String
     */
    @RequestMapping(value = "/admin/users/delete/{pnc}", method = RequestMethod.GET)
    public String deleteUser(@PathVariable String pnc, ModelMap model,
        RedirectAttributes redirectAttributes) {
        try {
            administrationUserService.deleteUser(pnc);
        } catch (ServiceEntityNotFoundException e) {
            redirectAttributes.addFlashAttribute("errorMessage", "The user does not exists or no longer exists");
        }

        return "redirect:/admin/users";
    }

    /**
     * Returns a form to update a user whose pnc is pnc.
     *
     * @param pnc                the pnc of the user
     * @param model              ModelMap
     * @param redirectAttributes RedirectAttributes
     * @return String
     */
    @RequestMapping(value = "/admin/users/edit/{pnc}", method = RequestMethod.GET)
    public String editUser(@PathVariable String pnc, ModelMap model,
        RedirectAttributes redirectAttributes) {
        try {
            UserDto userDto = administrationUserService.getUserByPnc(pnc);
            userDto.setPassword("");
            FormUserDto formUserDto = administrationFormService.getFormUser();

            model.addAttribute("userDto", userDto);
            model.addAttribute("formUserDto", formUserDto);

            return "updateUser";
        } catch (ServiceEntityNotFoundException e) {
            redirectAttributes.addFlashAttribute("errorMessage", "The user does not exists or no longer exists");

            return "redirect:/admin/users";
        }
    }

    /**
     * Checks if the user model is valid, then updates it. If the user model is not valid the same page will be returned.
     *
     * @param userDto            the user model
     * @param bindingResult      BindingResult
     * @param model              ModelMap
     * @param pnc                the pnc of the user
     * @param redirectAttributes RedirectAttributes
     * @return String
     */
    @RequestMapping(value = "/admin/users/edit/{pnc}", method = RequestMethod.POST)
    public String updateUser(@Valid UserDto userDto, BindingResult bindingResult, ModelMap model,
        @PathVariable String pnc, RedirectAttributes redirectAttributes) {

        if ((bindingResult.getErrorCount() > 1) ||
            (bindingResult.getErrorCount() == 1 && !userDto.getPassword().equals(""))) {
            FormUserDto formUserDto = administrationFormService.getFormUser();

            model.addAttribute("formUserDto", formUserDto);

            return "updateUser";
        }

        try {

            if (userDto.getPassword().equals("")) {
                administrationUserService.updateUserNoPassword(userDto);
            }
            else {
                userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
                administrationUserService.updateUser(userDto);

            }

            return "redirect:/admin/users";
        } catch (ServiceEntityNotFoundException e) {
            redirectAttributes.addFlashAttribute("errorMessage", "The user does not exists or no longer exists");

            return "redirect:/admin/users";
        }
    }

    /**
     * Returns a JSON object that consists on the fields that the user specified by his/her pnc already exists in the database.
     *
     * @param pnc the pnc of the user
     * @return String
     */
    @RequestMapping(value = "/admin/users/new/ajax", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    public
    @ResponseBody
    UnregisteredUserDto getUser(@RequestBody String pnc) {
        pnc = pnc.substring(1, pnc.length() - 1);
        try {
            return administrationUserService.getUnregisteredUser(pnc);

        } catch (ServiceEntityNotFoundException e) {
            return new UnregisteredUserDto();
        }
    }

}
