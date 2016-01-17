package com.iquestint.jms.email;

import javax.xml.bind.annotation.XmlRegistry;

/**
 * This class is an object factory which is needed for the XML marshaller.
 *
 * @author Georgian Vladutu
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
