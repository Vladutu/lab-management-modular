package com.iquestint.service.impl;

import com.iquestint.dao.NoteDao;
import com.iquestint.dto.NoteDto;
import com.iquestint.exception.DaoEntityAlreadyExistsException;
import com.iquestint.exception.DaoEntityNotFoundException;
import com.iquestint.exception.ServiceEntityAlreadyExistsException;
import com.iquestint.exception.ServiceEntityNotFoundException;
import com.iquestint.model.Note;
import com.iquestint.populator.NotePopulator;
import com.iquestint.service.NoteService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @author vladu
 */
@Service("noteService")
@Transactional
public class NoteServiceImpl implements NoteService {

    @Autowired
    private NoteDao noteDao;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private NotePopulator notePopulator;

    @Override
    public List<NoteDto> getAllNotes() {
        List<Note> notes = noteDao.getAllNotes();

        return modelMapper.map(notes, new TypeToken<List<NoteDto>>() {
        }.getType());
    }

    @Override
    public void deleteNode(int noteId) throws ServiceEntityNotFoundException {
        try {
            noteDao.deleteNote(noteId);
        } catch (DaoEntityNotFoundException e) {
            throw new ServiceEntityNotFoundException(e);
        }
    }

    @Override
    public void saveNote(NoteDto noteDto, int laboratoryId) throws ServiceEntityAlreadyExistsException {
        Note note = notePopulator.populate(noteDto, laboratoryId);
        try {
            noteDao.saveNote(note);
        } catch (DaoEntityAlreadyExistsException e) {
            throw new ServiceEntityAlreadyExistsException(e);
        }
    }
}
