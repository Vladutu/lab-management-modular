package com.iquestint.mapper;

import com.iquestint.dto.LaboratoryDto;
import com.iquestint.model.Laboratory;
import org.modelmapper.PropertyMap;

/**
 * This class is used by ModelMapper to map from Laboratory class to LaboratoryDto class.
 *
 * @author Georgian Vladutu
 */
public class LaboratoryToLaboratoryDtoMap extends PropertyMap<Laboratory, LaboratoryDto> {

    @Override
    protected void configure() {
        map(source.getId(), destination.getId());
        map(source.getName(), destination.getName());
        map(source.getSemester().getValue(), destination.getSemester());
        map(source.getSection().getName(), destination.getSection());
        map(source.getYear().getValue(), destination.getYear());
        map(source.getDay().getValue(), destination.getDay());
        map(source.getFrom().getValue(), destination.getFrom());
        map(source.getTo().getValue(), destination.getTo());
        map(source.getGroup().getName(), destination.getGroup());
        map(source.getSubgroup().getName(), destination.getSubgroup());
        map(source.getRoom().getName(), destination.getRoom());
        map(source.getProfessor(), destination.getFormProfessorDto());
        map(source.getWeeklyOccurrence().getName(), destination.getWeeklyOccurrence());
    }
}
