package com.iquestint.mapper;

import com.iquestint.dto.UserDto;
import com.iquestint.model.User;
import org.modelmapper.PropertyMap;

/**
 * @author vladu
 */
public class UserDtoToUserMap extends PropertyMap<UserDto, User> {

    @Override
    protected void configure() {
        map(source.getPnc(), destination.getPnc());
        map(source.getFirstName(), destination.getPerson().getFirstName());
        map(source.getLastName(), destination.getPerson().getLastName());
        map(source.getEmail(), destination.getPerson().getEmail());
        map(source.getPassword(), destination.getPassword());
        map(source.getUserType(), destination.getUserType().getName());
        map(source.getUserState(), destination.getUserState().getName());
    }
}
