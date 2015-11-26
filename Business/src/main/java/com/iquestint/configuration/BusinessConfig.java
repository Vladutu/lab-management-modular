package com.iquestint.configuration;

import com.iquestint.mapper.StudentDtoToStudentMap;
import com.iquestint.mapper.StudentToStudentDtoMap;
import com.iquestint.mapper.UserDtoToUserMap;
import com.iquestint.mapper.UserToUserDtoMap;
import com.iquestint.model.Laboratory;
import com.iquestint.model.Professor;
import com.iquestint.notUsed.LaboratoryDto;
import com.iquestint.notUsed.SimplifiedProfessorDto;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * This class is used by the IoC Spring container as a source for bean definitions.
 *
 * @author Georgian Vladutu
 */
@Configuration
@EnableTransactionManagement
@ComponentScan({ "com.iquestint" })
public class BusinessConfig {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();

        PropertyMap<Professor, SimplifiedProfessorDto> professorSimplifiedProfessorDtoPropertyMap = new PropertyMap<Professor, SimplifiedProfessorDto>() {
            @Override
            protected void configure() {
                map(source.getPnc(), destination.getPnc());
                map(source.getFirstName(), destination.getFirstName());
                map(source.getLastName(), destination.getLastName());
            }
        };

        PropertyMap<SimplifiedProfessorDto, Professor> simplifiedProfessorDtoProfessorPropertyMap = new PropertyMap<SimplifiedProfessorDto, Professor>() {
            @Override
            protected void configure() {
                map(source.getPnc(), destination.getPnc());
                map(source.getFirstName(), destination.getFirstName());
                map(source.getLastName(), destination.getLastName());
            }
        };

        PropertyMap<Laboratory, LaboratoryDto> laboratoryLaboratoryDtoPropertyMap = new PropertyMap<Laboratory, LaboratoryDto>() {
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
                map(source.getProfessor(), destination.getProfessorDto());
                map(source.getWeeklyOccurrence().getName(), destination.getWeeklyOccurrence());
            }
        };

        PropertyMap<LaboratoryDto, Laboratory> laboratoryDtoLaboratoryPropertyMap = new PropertyMap<LaboratoryDto, Laboratory>() {
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
                map(source.getProfessorDto(), destination.getProfessor());
                map(source.getWeeklyOccurrence(), destination.getWeeklyOccurrence().getName());
            }
        };

        modelMapper.addMappings(new StudentToStudentDtoMap());
        modelMapper.addMappings(new StudentDtoToStudentMap());
        modelMapper.addMappings(new UserToUserDtoMap());
        modelMapper.addMappings(new UserDtoToUserMap());

        return modelMapper;
    }
}
