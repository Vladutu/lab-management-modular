package com.iquestint.populator;

import com.iquestint.dto.FormUserDto;
import com.iquestint.dto.UserDto;
import org.springframework.stereotype.Component;

/**
 * @author vladu
 */
@Component
public class FormUserDtoPopulator {

    public void populateFormUserDto(FormUserDto formUserDto, UserDto userDto) {
        formUserDto.setPnc(userDto.getPnc());
        formUserDto.setLastName(userDto.getLastName());
        formUserDto.setFirstName(userDto.getFirstName());
        formUserDto.setEmail(userDto.getEmail());
        formUserDto.setPassword(userDto.getPassword());
        formUserDto.setUserType(userDto.getUserType());
    }
}
