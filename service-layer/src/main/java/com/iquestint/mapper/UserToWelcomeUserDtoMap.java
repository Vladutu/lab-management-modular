package com.iquestint.mapper;

import com.iquestint.dto.WelcomeUserDto;
import com.iquestint.model.User;
import org.modelmapper.PropertyMap;

/**
 * @author vladu
 */
public class UserToWelcomeUserDtoMap extends PropertyMap<User, WelcomeUserDto> {

    @Override
    protected void configure() {
        map(source.getPnc(), destination.getPnc());
    }
}
