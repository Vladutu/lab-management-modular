package com.iquestint.controller;

import com.iquestint.dto.*;
import com.iquestint.exception.ServiceEntityAlreadyExistsException;
import com.iquestint.exception.ServiceEntityNotFoundException;
import com.iquestint.exception.laboratory.ControllerLaboratoryAlreadyExistsException;
import com.iquestint.exception.laboratory.ControllerLaboratoryBindingException;
import com.iquestint.exception.laboratory.ControllerLaboratoryFieldsNotFoundException;
import com.iquestint.exception.laboratory.ControllerLaboratoryNotFoundException;
import com.iquestint.service.AdministrationFormService;
import com.iquestint.service.AdministrationLaboratoryService;
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
 * This class is a REST controller used for operations on Laboratory objects.
 *
 * @author Georgian Vladutu
 */
@RestController
@RequestMapping(value = "/admin")
public class AdministrationLaboratoriesController {

    @Autowired
    private AdministrationLaboratoryService administrationLaboratoryService;

    @Autowired
    private AdministrationFormService administrationFormService;

    /**
     * Returns all existing laboratories which have the same section, year and semester as the method parameters.
     *
     * @param section  section
     * @param year     year
     * @param semester semester
     * @return list of laboratories
     */
    @RequestMapping(value = "/laboratories/{section}/{year}/{semester}", method = RequestMethod.GET)
    public ResponseEntity<List<LaboratoryDto>> getLaboratories(@PathVariable String section, @PathVariable int year,
        @PathVariable int semester) {
        List<LaboratoryDto> laboratories = administrationLaboratoryService.getLaboratories(new SectionDto(section),
            new YearDto(year), new SemesterDto(semester));

        return new ResponseEntity<List<LaboratoryDto>>(laboratories, HttpStatus.OK);
    }

    /**
     * Returns laboratory categories.
     *
     * @return list of laboratory categories
     */
    @RequestMapping(value = "/laboratories/categories", method = RequestMethod.GET)
    public ResponseEntity<FormLaboratoryShowDto> getLaboratoryCategories() {
        FormLaboratoryShowDto categories = administrationFormService.getFormLaboratoryShowDto();

        return new ResponseEntity<FormLaboratoryShowDto>(categories, HttpStatus.OK);
    }

    /**
     * Saves a new laboratory.
     *
     * @param laboratory           laboratory to be saved
     * @param bindingResult        bindingResult
     * @param uriComponentsBuilder uriComponentsBuilder
     * @return a corresponding status code and a message if there is an error
     * @throws ControllerLaboratoryBindingException        if the user input and the java object cannot be bound together
     * @throws ControllerLaboratoryFieldsNotFoundException if any the laboratory's fields are incorrect
     * @throws ControllerLaboratoryAlreadyExistsException  if the laboratory already exists
     */
    @RequestMapping(value = "/laboratories", method = RequestMethod.POST)
    public ResponseEntity<Void> createLaboratory(@Valid @RequestBody LaboratoryDto laboratory,
        BindingResult bindingResult,
        UriComponentsBuilder uriComponentsBuilder)
        throws ControllerLaboratoryBindingException, ControllerLaboratoryFieldsNotFoundException,
        ControllerLaboratoryAlreadyExistsException {
        if (bindingResult.hasErrors()) {
            throw new ControllerLaboratoryBindingException(bindingResult.getFieldErrors());
        }

        try {
            administrationLaboratoryService.saveLaboratory(laboratory);
        } catch (ServiceEntityNotFoundException e) {
            throw new ControllerLaboratoryFieldsNotFoundException();
        } catch (ServiceEntityAlreadyExistsException e) {
            throw new ControllerLaboratoryAlreadyExistsException();
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(uriComponentsBuilder.path("/laboratories/{id}").buildAndExpand(laboratory.getId()).toUri());

        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }

    /**
     * Deletes the laboratory whose id is laboratoryId.
     *
     * @param laboratoryId the id of the laboratory to be deleted
     * @return a corresponding status code and a message if there is an error
     * @throws ControllerLaboratoryNotFoundException if the laboratory is not found
     */
    @RequestMapping(value = "/laboratories/{laboratoryId}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteStudent(@PathVariable int laboratoryId)
        throws ControllerLaboratoryNotFoundException {
        try {
            administrationLaboratoryService.deleteLaboratory(laboratoryId);
        } catch (ServiceEntityNotFoundException e) {
            throw new ControllerLaboratoryNotFoundException();
        }

        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }

    /**
     * Updates the laboratory whose id is laboratoryId.
     *
     * @param laboratoryId the id of the laboratory
     * @param laboratory   laboratory to be updated
     * @return a corresponding status code and a message if there is an error
     * @throws ControllerLaboratoryNotFoundException if the laboratory is not found
     */
    @RequestMapping(value = "/laboratories/{laboratoryId}", method = RequestMethod.PUT)
    public ResponseEntity<LaboratoryDto> updateLaboratory(@PathVariable int laboratoryId,
        @RequestBody LaboratoryDto laboratory) throws ControllerLaboratoryNotFoundException {
        try {
            laboratory.setId(laboratoryId);
            administrationLaboratoryService.updateLaboratory(laboratory);
        } catch (ServiceEntityNotFoundException e) {
            throw new ControllerLaboratoryNotFoundException();
        }

        LaboratoryDto updatedLaboratory = null;
        try {
            updatedLaboratory = administrationLaboratoryService.getLaboratoryById(laboratoryId);
        } catch (ServiceEntityNotFoundException ignored) {
        }

        return new ResponseEntity<LaboratoryDto>(updatedLaboratory, HttpStatus.OK);
    }
}
