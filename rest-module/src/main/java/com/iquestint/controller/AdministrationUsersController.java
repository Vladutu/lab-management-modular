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
 * This class is a REST controller used for operations on User objects.
 *
 * @author Georgian Vladutu
 */
@RestController
@RequestMapping(value = "/admin")
public class AdministrationUsersController {

    @Autowired
    private AdministrationUserService administrationUserService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * Returns all existing users.
     *
     * @return a list of users
     */
    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public ResponseEntity<List<UserDto>> getAllUsers() {
        List<UserDto> users = administrationUserService.getAllUsers();

        return new ResponseEntity<List<UserDto>>(users, HttpStatus.OK);
    }

    /**
     * Saves a new user.
     *
     * @param user                 user to be saved
     * @param bindingResult        bindingResult
     * @param uriComponentsBuilder uriComponentsBuilder
     * @return a corresponding status code and a message if there is an error
     * @throws ControllerUserBindingException        if the user input and the java object cannot be bound together
     * @throws ControllerUserFieldsNotFoundException if any the user's fields are incorrect
     * @throws ControllerUserAlreadyExistsException  if the user already exists
     */
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

    /**
     * Deletes the user whose pnc is userPnc.
     *
     * @param userPnc the pnc of the user
     * @return a corresponding status code and a message if there is an error
     * @throws ControllerUserNotFoundException if the user is not found
     */
    @RequestMapping(value = "/users/{userPnc}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteUser(@PathVariable String userPnc) throws ControllerUserNotFoundException {
        try {
            administrationUserService.deleteUser(userPnc);
        } catch (ServiceEntityNotFoundException e) {
            throw new ControllerUserNotFoundException();
        }

        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }

    /**
     * Updates the user whose pnc is userPnc.
     *
     * @param userPnc the pnc of the user
     * @param user    user to be updated
     * @return a corresponding status code and a message if there is an error
     * @throws ControllerUserNotFoundException if the user is not found
     */
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

    /**
     * Returns a student or a professor who already exists in the repository but is not registered as a user yet.
     *
     * @param pnc pnc of the student or professor
     * @return the unregistered user or an error message if there is an error
     * @throws ControllerUserNotFoundException if the unregistered user is not found
     */
    @RequestMapping(value = "/users/ajax")
    public ResponseEntity<UnregisteredUserDto> getUnregisteredUser(@RequestBody String pnc)
        throws ControllerUserNotFoundException {
        pnc = pnc.substring(1, pnc.length() - 1);
        try {
            UnregisteredUserDto unregisteredUser = administrationUserService.getUnregisteredUser(pnc);

            return new ResponseEntity<UnregisteredUserDto>(unregisteredUser, HttpStatus.OK);
        } catch (ServiceEntityNotFoundException e) {
            throw new ControllerUserNotFoundException();
        }
    }
}
