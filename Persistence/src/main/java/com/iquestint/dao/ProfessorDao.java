package com.iquestint.dao;

import com.iquestint.exception.DaoEntityAlreadyExists;
import com.iquestint.exception.DaoEntityNotFoundException;
import com.iquestint.model.Professor;

import java.util.List;

/**
 * @author vladu
 */
public interface ProfessorDao {

    List<Professor> findAllProfessors();

    Professor findByPnc(String pnc) throws DaoEntityNotFoundException;

    Professor findProfessorByName(String firstName, String lastName) throws DaoEntityNotFoundException;

    void saveProfessor(Professor professor) throws DaoEntityAlreadyExists;

    void updateProfessor(Professor professor) throws DaoEntityNotFoundException;

    void deleteProfessorByPnc(String pnc) throws DaoEntityNotFoundException;
}

