package com.iquestint.controller;

import com.iquestint.dto.ProfessorDto;
import com.iquestint.exception.ServiceEntityAlreadyExistsException;
import com.iquestint.exception.ServiceEntityNotFoundException;
import com.iquestint.exception.professor.ControllerProfessorAlreadyExistsException;
import com.iquestint.exception.professor.ControllerProfessorBindingException;
import com.iquestint.exception.professor.ControllerProfessorNotFoundException;
import com.iquestint.service.AdministrationProfessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.util.List;

/**
 * @author vladu
 */
@RestController
public class AdministrationProfessorsController {

    @Autowired
    private AdministrationProfessorService administrationProfessorService;

    @RequestMapping(value = "/professors", method = RequestMethod.GET)
    public ResponseEntity<List<ProfessorDto>> getAllProfessors() {
        List<ProfessorDto> professors = administrationProfessorService.getAllProfessors();

        return new ResponseEntity<List<ProfessorDto>>(professors, HttpStatus.OK);
    }

    @RequestMapping(value = "/professors", method = RequestMethod.POST)
    public ResponseEntity<Void> createProfessor(@Valid @RequestBody ProfessorDto professor, BindingResult bindingResult,
        UriComponentsBuilder uriComponentsBuilder)
        throws ControllerProfessorBindingException, ControllerProfessorAlreadyExistsException {
        if (bindingResult.hasErrors()) {
            throw new ControllerProfessorBindingException(bindingResult.getFieldErrors());
        }

        try {
            administrationProfessorService.saveProfessor(professor);
        } catch (ServiceEntityAlreadyExistsException e) {
            throw new ControllerProfessorAlreadyExistsException();
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(uriComponentsBuilder.path("/professors/{pnc}").buildAndExpand(professor.getPnc()).toUri());

        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/professors/{professorPnc}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteProfessor(@PathVariable String professorPnc)
        throws ControllerProfessorNotFoundException {
        try {
            administrationProfessorService.deleteProfessor(professorPnc);
        } catch (ServiceEntityNotFoundException e) {
            throw new ControllerProfessorNotFoundException();
        }

        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "/professors/{professorPnc}", method = RequestMethod.PUT)
    public ResponseEntity<ProfessorDto> updateProfessor(@PathVariable String professorPnc,
        @RequestBody ProfessorDto professor) throws ControllerProfessorNotFoundException {
        try {
            professor.setPnc(professorPnc);
            administrationProfessorService.updateProfessor(professor);
        } catch (ServiceEntityNotFoundException e) {
            throw new ControllerProfessorNotFoundException();
        }

        ProfessorDto updatedProfessor = null;
        try {
            updatedProfessor = administrationProfessorService.getProfessorByPnc(professorPnc);
        } catch (ServiceEntityNotFoundException ignored) {
        }

        return new ResponseEntity<ProfessorDto>(updatedProfessor, HttpStatus.OK);
    }
}
