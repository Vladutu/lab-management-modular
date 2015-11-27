package com.iquestint.mapper;

import com.iquestint.model.Laboratory;
import com.iquestint.dto.LaboratoryDto;
import org.modelmapper.PropertyMap;

/**
 * @author vladu
 */
public class LaboratoryDtoToLaboratoryMap extends PropertyMap<LaboratoryDto, Laboratory> {

    @Override
    protected void configure() {
        map(source.getId(), destination.getId());
        map(source.getName(), destination.getName());
        map(source.getSemester(), destination.getSemester().getValue());
        map(source.getSection(), destination.getSection().getName());
        map(source.getYear(), destination.getYear().getValue());
        map(source.getDay(), destination.getDay().getValue());
        map(source.getFrom(), destination.getFrom().getValue());
        map(source.getTo(), destination.getTo().getValue());
        map(source.getGroup(), destination.getGroup().getName());
        map(source.getSubgroup(), destination.getSubgroup().getName());
        map(source.getRoom(), destination.getRoom().getName());
        map(source.getFormProfessorDto(), destination.getProfessor());
        map(source.getWeeklyOccurrence(), destination.getWeeklyOccurrence().getName());
    }
}
