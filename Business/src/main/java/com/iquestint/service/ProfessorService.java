package com.iquestint.service;

import com.iquestint.exception.ServiceEntityAlreadyExistsException;
import com.iquestint.exception.ServiceEntityNotFoundException;
import com.iquestint.model.Professor;

import java.util.List;

/**
 * @author vladu
 */
public interface ProfessorService {

    void saveProfessor(Professor professor) throws ServiceEntityAlreadyExistsException;

    void deleteProfessor(String pnc) throws ServiceEntityNotFoundException;

    Professor getProfessorByPnc(String pnc) throws ServiceEntityNotFoundException;

    List<Professor> getAllProfessors();

    void updateProfessor(Professor professor) throws ServiceEntityNotFoundException;

    Professor getProfessorByName(String firstName, String lastName) throws ServiceEntityNotFoundException;
}
