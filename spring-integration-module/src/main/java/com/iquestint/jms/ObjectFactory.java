package com.iquestint.jms;

import com.iquestint.jms.email.EmailRequest;
import com.iquestint.jms.email.EmailResponse;

import javax.xml.bind.annotation.XmlRegistry;

/**
 * @author vladu
 */
@XmlRegistry
public class ObjectFactory {

    public EmailRequest createEmailRequest() {
        return new EmailRequest();
    }

    public EmailResponse createEmailResponse() {
        return new EmailResponse();
    }
}