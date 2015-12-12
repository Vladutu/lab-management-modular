package com.iquestint.mapper;

import com.iquestint.dto.WelcomeUserDto;
import com.iquestint.model.Person;
import org.modelmapper.PropertyMap;

/**
 * This class is used by ModelMapper to map from Person class to WelcomeUserDto class.
 *
 * @author Georgian Vladutu
 */
public class PersonToWelcomeUserDtoMap extends PropertyMap<Person, WelcomeUserDto> {

    @Override
    protected void configure() {
        map(source.getPnc(), destination.getPnc());
        map(source.getFirstName(), destination.getFirstName());
        map(source.getLastName(), destination.getLastName());
    }
}
