package com.iquestint.dao;

import com.iquestint.exception.DaoEntityAlreadyExistsException;
import com.iquestint.exception.DaoEntityNotFoundException;
import com.iquestint.model.Note;

import java.util.List;

/**
 * @author vladu
 */
public interface NoteDao {

    void saveNote(Note note) throws DaoEntityAlreadyExistsException;

    List<Note> getAllNotes();

    void deleteNote(int noteId) throws DaoEntityNotFoundException;

    Note getNote(int id) throws DaoEntityNotFoundException;

    void deleteNotesByLaboratory(int laboratoryId);
}
