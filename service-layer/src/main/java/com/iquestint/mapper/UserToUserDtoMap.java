package com.iquestint.mapper;

import com.iquestint.dto.UserDto;
import com.iquestint.model.User;
import org.modelmapper.PropertyMap;

/**
 * This class is used by ModelMapper to map from User class to UserDto class.
 *
 * @author Georgian Vladutu
 */
public class UserToUserDtoMap extends PropertyMap<User, UserDto> {

    @Override
    protected void configure() {
        map(source.getPnc(), destination.getPnc());
        map(source.getPerson().getFirstName(), destination.getFirstName());
        map(source.getPerson().getLastName(), destination.getLastName());
        map(source.getPerson().getEmail(), destination.getEmail());
        map(source.getPassword(), destination.getPassword());
        map(source.getUserType().getName(), destination.getUserType());
        map(source.getUserState().getName(), destination.getUserState());
    }
}
