package com.iquestint.controller;

import com.iquestint.dto.UserDto;
import com.iquestint.dto.UserTypeDto;
import com.iquestint.enums.Type;
import com.iquestint.exception.ServiceEntityNotFoundException;
import com.iquestint.model.Professor;
import com.iquestint.model.Student;
import com.iquestint.model.User;
import com.iquestint.model.UserType;
import com.iquestint.service.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public String getUsers(ModelMap model) {
        List<User> users = userService.getAllUsers();
        List<UserDto> userDtos = new ArrayList<>();

        for (User user : users) {
            userDtos.add(modelMapper.map(user, UserDto.class));
        }

        model.addAttribute("users", userDtos);

        return "listUsers";
    }

    @RequestMapping(value = "/users/new", method = RequestMethod.GET)
    public String newUser(ModelMap model) {
        initalizeUserDto(model);

        return "createUser";
    }

    @RequestMapping(value = "/users/new/ajax", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
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
