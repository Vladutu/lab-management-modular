package com.iquestint.dao.impl;

import com.iquestint.dao.NoteDao;
import com.iquestint.exception.DaoEntityAlreadyExistsException;
import com.iquestint.exception.DaoEntityNotFoundException;
import com.iquestint.model.Note;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * This class implements NoteDao interface;
 *
 * @author Georgian Vladutu
 */
@Repository("noteDao")
public class NoteDaoImpl extends JpaDao<Note> implements NoteDao {

    @Override
    public void saveNote(Note note) throws DaoEntityAlreadyExistsException {
        try {
            Note n = getNote(note.getId());
        } catch (DaoEntityNotFoundException e) {
            persist(note);
            return;
        }

        throw new DaoEntityAlreadyExistsException();
    }

    @Override
    public List<Note> getAllNotes() {
        TypedQuery<Note> query = getEntityManager().createQuery("SELECT n FROM Note n ORDER BY n.date DESC",
            Note.class);

        return query.getResultList();
    }

    @Override
    public void deleteNote(int noteId) throws DaoEntityNotFoundException {
        Note note = getNote(noteId);
        delete(note);
    }

    @Override
    public Note getNote(int id) throws DaoEntityNotFoundException {
        return getById(id);
    }

    @Override
    public void deleteNotesByLaboratory(int laboratoryId) {
        Query query = getEntityManager().createQuery("DELETE  FROM Note n WHERE n.laboratory.id = :id");
        query.setParameter("id", laboratoryId);

        query.executeUpdate();
    }

    @Override
    public List<Note> getNotesByLaboratory(int laboratoryId) {
        TypedQuery<Note> query = getEntityManager().createQuery(
            "SELECT n FROM Note n WHERE n.laboratory.id = :id", Note.class);
        query.setParameter("id", laboratoryId);

        return query.getResultList();
    }
}
