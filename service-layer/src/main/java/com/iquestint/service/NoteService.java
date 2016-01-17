package com.iquestint.service;

import com.iquestint.dto.NoteDto;
import com.iquestint.exception.ServiceEntityAlreadyExistsException;
import com.iquestint.exception.ServiceEntityNotFoundException;

import java.util.List;

/**
 * This interfaces provides methods for notes management.
 *
 * @author Georgian Vladutu
 */
public interface NoteService {

    /**
     * Returns all notes which belong to the laboratory whose id is laboratoryId from the repository.
     *
     * @param laboratoryId id of the laboratory
     * @return list of notes
     */
    List<NoteDto> getNotesByLaboratory(int laboratoryId);

    /**
     * Deletes the note whose id is noteId from the repository.
     *
     * @param noteId id of the note
     * @throws ServiceEntityNotFoundException if the note is not found
     */
    void deleteNode(int noteId) throws ServiceEntityNotFoundException;

    /**
     * Saves the note which belong to the laboratory whose id is laboratoryId in the repository.
     *
     * @param noteDto      note to be saved
     * @param laboratoryId id of the laboratory
     * @throws ServiceEntityAlreadyExistsException if the note already exists
     */
    void saveNote(NoteDto noteDto, int laboratoryId) throws ServiceEntityAlreadyExistsException;
}
