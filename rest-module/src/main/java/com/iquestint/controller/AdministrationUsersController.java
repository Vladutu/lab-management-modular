package com.iquestint.controller;

import com.iquestint.dto.UnregisteredUserDto;
import com.iquestint.dto.UserDto;
import com.iquestint.enums.State;
import com.iquestint.exception.ServiceEntityAlreadyExistsException;
import com.iquestint.exception.ServiceEntityNotFoundException;
import com.iquestint.exception.user.ControllerUserAlreadyExistsException;
import com.iquestint.exception.user.ControllerUserBindingException;
import com.iquestint.exception.user.ControllerUserFieldsNotFoundException;
import com.iquestint.exception.user.ControllerUserNotFoundException;
import com.iquestint.service.AdministrationUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.util.List;

/**
 * @author vladu
 */
@RestController
public class AdministrationUsersController {

    @Autowired
    private AdministrationUserService administrationUserService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public ResponseEntity<List<UserDto>> getAllUsers() {
        List<UserDto> users = administrationUserService.getAllUsers();

        return new ResponseEntity<List<UserDto>>(users, HttpStatus.OK);
    }

    @RequestMapping(value = "/users", method = RequestMethod.POST)
    public ResponseEntity<Void> createUser(@Valid @RequestBody UserDto user, BindingResult bindingResult,
        UriComponentsBuilder uriComponentsBuilder)
        throws ControllerUserBindingException, ControllerUserFieldsNotFoundException,
        ControllerUserAlreadyExistsException {
        if (bindingResult.hasErrors()) {
            throw new ControllerUserBindingException(bindingResult.getFieldErrors());
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setUserState(State.ACTIVE.getState());

        try {
            administrationUserService.saveUser(user);
        } catch (ServiceEntityNotFoundException e) {
            throw new ControllerUserFieldsNotFoundException();
        } catch (ServiceEntityAlreadyExistsException e) {
            throw new ControllerUserAlreadyExistsException();
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(uriComponentsBuilder.path("/users/{pnc}").buildAndExpand(user.getPnc()).toUri());

        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/users/{userPnc}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteUser(@PathVariable String userPnc) throws ControllerUserNotFoundException {
        try {
            administrationUserService.deleteUser(userPnc);
        } catch (ServiceEntityNotFoundException e) {
            throw new ControllerUserNotFoundException();
        }

        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "/users/{userPnc}", method = RequestMethod.PUT)
    public ResponseEntity<UserDto> updateUser(@PathVariable String userPnc, @RequestBody UserDto user)
        throws ControllerUserNotFoundException {
        try {
            user.setPnc(userPnc);
            administrationUserService.updateUser(user);
        } catch (ServiceEntityNotFoundException e) {
            throw new ControllerUserNotFoundException();
        }

        UserDto updatedUser = null;
        try {
            updatedUser = administrationUserService.getUserByPnc(userPnc);
        } catch (ServiceEntityNotFoundException ignored) {
        }

        return new ResponseEntity<UserDto>(updatedUser, HttpStatus.OK);
    }

    @RequestMapping(value = "/users/ajax")
    public ResponseEntity<UnregisteredUserDto> getUser(@RequestBody String pnc) throws ControllerUserNotFoundException {
        pnc = pnc.substring(1, pnc.length() - 1);
        try {
            UnregisteredUserDto unregisteredUser = administrationUserService.getUnregisteredUser(pnc);

            return new ResponseEntity<UnregisteredUserDto>(unregisteredUser, HttpStatus.OK);
        } catch (ServiceEntityNotFoundException e) {
            throw new ControllerUserNotFoundException();
        }
    }
}
