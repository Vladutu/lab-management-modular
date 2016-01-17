package com.iquestint.mapper;

import com.iquestint.convereter.LocalDateTimeToStringConveter;
import com.iquestint.dto.NoteDto;
import com.iquestint.model.Note;
import org.modelmapper.PropertyMap;

/**
 * @author vladu
 */
public class NoteToNoteDtoMap extends PropertyMap<Note, NoteDto> {

    @Override
    protected void configure() {
        map(source.getId(), destination.getId());
        map(source.getMessage(), destination.getMessage());
        using(new LocalDateTimeToStringConveter()).map(source.getDate(), destination.getDate());
    }
}
