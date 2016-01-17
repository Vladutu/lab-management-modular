package com.iquestint.dao;

import com.iquestint.exception.DaoEntityAlreadyExistsException;
import com.iquestint.exception.DaoEntityNotFoundException;
import com.iquestint.model.Note;

import java.util.List;

/**
 * This interfaces provides methods for working with Note entity explicitly (and Note database table implicitly).
 *
 * @author Georgian Vladutu
 */
public interface NoteDao {

    /**
     * Saves the Note entity into the database.
     *
     * @param note note to be saved
     * @throws DaoEntityAlreadyExistsException if the entity already exists into the database
     */
    void saveNote(Note note) throws DaoEntityAlreadyExistsException;

    /**
     * Returns all Note entity from the database.
     *
     * @return list of notes
     */
    List<Note> getAllNotes();

    /**
     * Deletes the note whose id is noteId from the database.
     *
     * @param noteId id of the note
     * @throws DaoEntityNotFoundException if the entity is not found in the database
     */
    void deleteNote(int noteId) throws DaoEntityNotFoundException;

    /**
     * Returns the Note entity whose id is the same as the method parameter from the database.
     *
     * @param id id of the note
     * @return Note
     * @throws DaoEntityNotFoundException if the entity is not found in the database
     */
    Note getNote(int id) throws DaoEntityNotFoundException;

    /**
     * Deletes all notes which belong to the laboratory whose id is laboratoryId from the database.
     *
     * @param laboratoryId id of the laboratory
     */
    void deleteNotesByLaboratory(int laboratoryId);

    /**
     * Returns all Note entities which belong to the laboratory whose id is laboratoryId.
     *
     * @param laboratoryId id of the laboratory
     * @return list of notes
     */
    List<Note> getNotesByLaboratory(int laboratoryId);
}
