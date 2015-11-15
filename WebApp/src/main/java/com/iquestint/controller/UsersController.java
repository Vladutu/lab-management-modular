package com.iquestint.controller;

import com.iquestint.dto.UserDto;
import com.iquestint.dto.UserTypeDto;
import com.iquestint.enums.State;
import com.iquestint.enums.Type;
import com.iquestint.exception.ServiceEntityAlreadyExistsException;
import com.iquestint.exception.ServiceEntityNotFoundException;
import com.iquestint.model.*;
import com.iquestint.service.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * @author vladu
 */
@Controller
@RequestMapping("/")
public class UsersController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserStateService userStateService;

    @Autowired
    private UserTypeService userTypeService;

    @Autowired
    private PersonService personService;

    @Autowired
    private StudentService studentService;

    @Autowired
    private ProfessorService professorService;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    PasswordEncoder passwordEncoder;

    @RequestMapping(value = "/admin/users", method = RequestMethod.GET)
    public String getUsers(ModelMap model) {
        List<User> users = userService.getAllUsers();
        List<UserDto> userDtos = new ArrayList<>();

        for (User user : users) {
            userDtos.add(modelMapper.map(user, UserDto.class));
        }

        model.addAttribute("users", userDtos);

        return "listUsers";
    }

    @RequestMapping(value = "/admin/users/new", method = RequestMethod.GET)
    public String newUser(ModelMap model) {
        initalizeUserDto(model);

        return "createUser";
    }

    @RequestMapping(value = "/admin/users/new", method = RequestMethod.POST)
    public String saveUser(@Valid UserDto userDto, BindingResult bindingResult, ModelMap model,
        RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            initializeDtoList(model);

            return "createUser";
        }

        User user = modelMapper.map(userDto, User.class);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setUserState(new UserState(State.ACTIVE.getState()));

        try {
            userService.saveUser(user);
        }
        catch (ServiceEntityAlreadyExistsException e) {
            redirectAttributes.addFlashAttribute("errorMessage", "User already exists");

            return "redirect:/admin/users/new";
        }
        catch (ServiceEntityNotFoundException ignored) {
        }

        return "redirect:/admin/users";

    }

    @RequestMapping(value = "/admin/users/delete/{pnc}", method = RequestMethod.GET)
    public String deleteUser(@PathVariable String pnc, ModelMap model,
        RedirectAttributes redirectAttributes) {
        try {
            userService.deleteUser(pnc);
        }
        catch (ServiceEntityNotFoundException e) {
            redirectAttributes.addFlashAttribute("errorMessage", "The user does not exists or no longer exists");
        }

        return "redirect:/admin/users";
    }

    @RequestMapping(value = "/admin/users/edit/{pnc}", method = RequestMethod.GET)
    public String editUser(@PathVariable String pnc, ModelMap model,
        RedirectAttributes redirectAttributes) {
        try {
            User user = userService.getUserByPnc(pnc);
            UserDto userDto = modelMapper.map(user, UserDto.class);
            userDto.setPassword("");

            model.addAttribute("userDto", userDto);

            return "updateUser";
        }
        catch (ServiceEntityNotFoundException e) {
            redirectAttributes.addFlashAttribute("errorMessage", "The user does not exists or no longer exists");

            return "redirect:/admin/users";
        }
    }

    @RequestMapping(value = "/admin/users/edit/{pnc}", method = RequestMethod.POST)
    public String updateUser(@Valid UserDto userDto, BindingResult bindingResult, ModelMap model,
        @PathVariable String pnc, RedirectAttributes redirectAttributes) {

        if ((bindingResult.getErrorCount() > 1) ||
            (bindingResult.getErrorCount() == 1 && !userDto.getPassword().equals(""))) {
            return "updateUser";
        }

        try {
            User user = modelMapper.map(userDto, User.class);

            if (user.getPassword().equals("")) {
                userService.updateUserNoPassword(user);
            }
            else {
                user.setPassword(passwordEncoder.encode(user.getPassword()));
                userService.updateUser(user);

            }

            return "redirect:/admin/users";
        }
        catch (ServiceEntityNotFoundException e) {
            redirectAttributes.addFlashAttribute("errorMessage", "The user does not exists or no longer exists");

            return "redirect:/admin/users";
        }
    }

    @RequestMapping(value = "/admin/users/new/ajax", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    public
    @ResponseBody
    UserDto getUser(@RequestBody String pnc) {
        pnc = pnc.substring(1, pnc.length() - 1);
        UserDto userDto = new UserDto();
        try {
            Type personType = personService.getPersonType(pnc);
            if (personType.equals(Type.STUDENT)) {
                Student student = studentService.getStudentByPnc(pnc);
                userDto.setLastName(student.getLastName());
                userDto.setFirstName(student.getFirstName());
                userDto.setUserType(personType.getType());
            }
            else if (personType.equals(Type.PROFESSOR)) {
                Professor professor = professorService.getProfessorByPnc(pnc);
                userDto.setLastName(professor.getLastName());
                userDto.setFirstName(professor.getFirstName());
                userDto.setUserType(personType.getType());
                userDto.setEmail(professor.getEmail());
            }

            return userDto;
        }
        catch (ServiceEntityNotFoundException e) {
            return userDto;
        }
    }

    private void initalizeUserDto(ModelMap model) {
        UserDto userDto = new UserDto();
        model.addAttribute("userDto", userDto);
        initializeDtoList(model);
    }

    private void initializeDtoList(ModelMap model) {
        List<UserType> userTypes = userTypeService.getAllUserTypes();
        List<UserTypeDto> userTypeDtos = new ArrayList<>();

        for (UserType userType : userTypes) {
            userTypeDtos.add(modelMapper.map(userType, UserTypeDto.class));
        }

        model.addAttribute("userTypeDtos", userTypeDtos);
    }
}
