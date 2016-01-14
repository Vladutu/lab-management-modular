package com.iquestint.jms.email;

import javax.xml.bind.annotation.XmlRegistry;

/**
 * @author vladu
 */
@XmlRegistry
public class ObjectFactory {

    public static EmailRequest createEmailRequest() {
        return new EmailRequest();
    }

    public static EmailResponse createEmailResponse() {
        return new EmailResponse();
    }
}