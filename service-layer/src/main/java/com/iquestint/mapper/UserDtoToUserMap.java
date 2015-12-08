package com.iquestint.mapper;

import com.iquestint.dto.UserDto;
import com.iquestint.model.User;
import org.modelmapper.PropertyMap;

/**
 * This class is used by ModelMapper to map from UserDto class to User class.
 *
 * @author Georgian Vladutu
 */
public class UserDtoToUserMap extends PropertyMap<UserDto, User> {

    @Override
    protected void configure() {
        map(source.getPnc(), destination.getPnc());
        map(source.getPassword(), destination.getPassword());
        map(source.getUserType(), destination.getUserType().getName());
        map(source.getUserState(), destination.getUserState().getName());
    }
}
