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
 * This class is a REST controller used for operations on Professor objects.
 *
 * @author Georgian Vladutu
 */
@RestController
@RequestMapping(value = "/admin")
public class AdministrationProfessorsController {

    @Autowired
    private AdministrationProfessorService administrationProfessorService;

    /**
     * Returns all existing professors.
     *
     * @return list of professors
     */
    @RequestMapping(value = "/professors", method = RequestMethod.GET)
    public ResponseEntity<List<ProfessorDto>> getAllProfessors() {
        List<ProfessorDto> professors = administrationProfessorService.getAllProfessors();

        return new ResponseEntity<List<ProfessorDto>>(professors, HttpStatus.OK);
    }

    /**
     * Saves a new professor.
     *
     * @param professor            professor to be saved
     * @param bindingResult        bindingResult
     * @param uriComponentsBuilder uriComponentsBuilder
     * @return a corresponding status code and a message if there is an error
     * @throws ControllerProfessorBindingException       if the user input and java object cannot be bound together
     * @throws ControllerProfessorAlreadyExistsException if the professor already exists
     */
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

    /**
     * Deletes the professor whose pnc is professorPnc.
     *
     * @param professorPnc the pnc of the professor
     * @return a corresponding status code and a message if there is an error
     * @throws ControllerProfessorNotFoundException if the professor is not found
     */
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

    /**
     * Updates the professor whose pnc is professorPnc.
     *
     * @param professorPnc the pnc of the professor
     * @param professor    the professor to be updated
     * @return a corresponding status code and a message if there is an error
     * @throws ControllerProfessorNotFoundException if the professor is not found
     */
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
