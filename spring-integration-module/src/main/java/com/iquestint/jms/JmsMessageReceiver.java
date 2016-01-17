package com.iquestint.jms;

import com.iquestint.jms.email.EmailResponse;
import org.springframework.messaging.Message;

/**
 * This class is used to receive a JMS message.
 *
 * @author Georgian Vladutu
 */
public interface JmsMessageReceiver {

    /**
     * This method receives the message from the JMS queue which represents the response of a sent email.
     *
     * @param responseMessage the response of the sent email
     */
    void processResponse(Message<EmailResponse> responseMessage);
}
