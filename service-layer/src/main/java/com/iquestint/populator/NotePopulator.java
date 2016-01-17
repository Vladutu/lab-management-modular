package com.iquestint.populator;

import com.iquestint.dto.NoteDto;
import com.iquestint.model.Laboratory;
import com.iquestint.model.Note;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * This class is used to populate a Note entity.
 *
 * @author Georgian Vladutu
 */
@Component
public class NotePopulator {

    public Note populate(NoteDto noteDto, int laboratoryId) {
        Note note = new Note();
        Laboratory laboratory = new Laboratory();
        laboratory.setId(laboratoryId);
        note.setDate(LocalDateTime.now());
        note.setLaboratory(laboratory);
        note.setMessage(noteDto.getMessage());

        return note;
    }
}
