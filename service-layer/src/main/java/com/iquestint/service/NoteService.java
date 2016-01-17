package com.iquestint.service;

import com.iquestint.dto.NoteDto;
import com.iquestint.exception.ServiceEntityAlreadyExistsException;
import com.iquestint.exception.ServiceEntityNotFoundException;

import java.util.List;

/**
 * @author vladu
 */
public interface NoteService {

    List<NoteDto> getAllNotes();

    void deleteNode(int noteId) throws ServiceEntityNotFoundException;

    void saveNote(NoteDto noteDto, int laboratoryId) throws ServiceEntityAlreadyExistsException;
}
