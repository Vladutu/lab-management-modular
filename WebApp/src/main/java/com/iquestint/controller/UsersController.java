package com.iquestint.controller;

import com.iquestint.dto.UserDto;
import com.iquestint.dto.UserStateDto;
import com.iquestint.dto.UserTypeDto;
import com.iquestint.enums.State;
import com.iquestint.enums.Type;
import com.iquestint.exception.ServiceEntityAlreadyExistsException;
import com.iquestint.exception.ServiceEntityNotFoundException;
import com.iquestint.mapper.UserMapper;
import com.iquestint.mapper.UserStateMapper;
import com.iquestint.mapper.UserTypeMapper;
import com.iquestint.model.*;
import com.iquestint.service.interfaces.*;
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
    PasswordEncoder passwordEncoder;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserStateMapper userStateMapper;

    @Autowired
    private UserTypeMapper userTypeMapper;

    /**
     * Returns all existing users.
     *
     * @param model ModelMap
     * @return String
     */
    @RequestMapping(value = "/admin/users", method = RequestMethod.GET)
    public String getUsers(ModelMap model) {
        List<User> users = userService.getAllUsers();
        List<UserDto> userDtos = userMapper.mapList(users);

        model.addAttribute("users", userDtos);

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
        initalizeUserDto(model);

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
            initializeDtoLists(model);

            return "createUser";
        }

        User user = userMapper.reverseMap(userDto);
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
            userService.deleteUser(pnc);
        }
        catch (ServiceEntityNotFoundException e) {
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
            User user = userService.getUserByPnc(pnc);
            UserDto userDto = userMapper.map(user);
            userDto.setPassword("");
            initializeDtoLists(model);

            model.addAttribute("userDto", userDto);

            return "updateUser";
        }
        catch (ServiceEntityNotFoundException e) {
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
            return "updateUser";
        }

        try {
            User user = userMapper.reverseMap(userDto);

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

    /**
     * Returns a JSON object that consists on the fields that the user specified by his/her pnc already exists in the database.
     *
     * @param pnc the pnc of the user
     * @return String
     */
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
                userDto.setEmail(student.getEmail());
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
        initializeDtoLists(model);
    }

    private void initializeDtoLists(ModelMap model) {
        List<UserType> userTypes = userTypeService.getAllUserTypes();
        List<UserState> userStates = userStateService.getAllUserStates();

        List<UserTypeDto> userTypeDtos = userTypeMapper.mapList(userTypes);
        List<UserStateDto> userStateDtos = userStateMapper.mapList(userStates);

        model.addAttribute("userTypeDtos", userTypeDtos);
        model.addAttribute("userStateDtos", userStateDtos);
    }
}
