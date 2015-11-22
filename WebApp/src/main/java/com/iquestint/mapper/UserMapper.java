package com.iquestint.mapper;

import com.iquestint.dto.UserDto;
import com.iquestint.model.User;
import org.springframework.stereotype.Component;

/**
 * @author vladu
 */
@Component
public class UserMapper extends MyMapper<User, UserDto> {
}
