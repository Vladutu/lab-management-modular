package com.iquestint.populator;

import com.iquestint.dto.UserDto;
import com.iquestint.jms.email.EmailRequest;
import org.springframework.stereotype.Component;

/**
 * This class is used to populate an EmailRequest object.
 *
 * @author Georgian Vladutu
 */
@Component
public class EmailRequestPopulator {

    public EmailRequest populate(UserDto userDto) {
        EmailRequest emailRequest = new EmailRequest();
        emailRequest.setSenderEmail(userDto.getEmail());
        emailRequest.setEmail("laboratory@ace.ucv.ro");
        emailRequest.setSubject("Account confimation");
        emailRequest.setBody("Your account has been created!");

        return emailRequest;
    }
}
