package com.iquestint.controller;

import com.iquestint.dto.UserDto;
import com.iquestint.service.AdministrationUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author vladu
 */
@RestController
public class TestController {

    @Autowired
    private AdministrationUserService administrationUserService;

    @RequestMapping(value = "/users/", method = RequestMethod.GET)
    public ResponseEntity<List<UserDto>> getAllUsers() {
        List<UserDto> userDtos = administrationUserService.getAllUsers();
        if (userDtos.isEmpty()) {
            return new ResponseEntity<List<UserDto>>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<List<UserDto>>(userDtos, HttpStatus.OK);
    }
}
