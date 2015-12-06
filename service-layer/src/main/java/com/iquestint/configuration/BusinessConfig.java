package com.iquestint.configuration;

import com.iquestint.mapper.*;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * This class is used by the IoC Spring container as a source for bean definitions.
 *
 * @author Georgian Vladutu
 */
@Configuration
@EnableTransactionManagement
@ComponentScan({ "com.iquestint" })
@PropertySource(value = { "classpath:semester.properties" })
public class BusinessConfig {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();

        modelMapper.addMappings(new StudentToStudentDtoMap());
        modelMapper.addMappings(new StudentDtoToStudentMap());
        modelMapper.addMappings(new UserToUserDtoMap());
        modelMapper.addMappings(new UserDtoToUserMap());
        modelMapper.addMappings(new LaboratoryToLaboratoryDtoMap());
        modelMapper.addMappings(new LaboratoryDtoToLaboratoryMap());
        modelMapper.addMappings(new ProfessorToFormProfessorDtoMap());
        modelMapper.addMappings(new FormProfessorDtoToProfessorMap());
        modelMapper.addMappings(new UserToWelcomeUserDtoMap());

        return modelMapper;
    }
}
