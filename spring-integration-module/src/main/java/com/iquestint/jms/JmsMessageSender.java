package com.iquestint.jms;

import com.iquestint.jms.email.EmailRequest;

/**
 * This class is used to send a JMS message.
 *
 * @author Georgian Vladutu
 */
public interface JmsMessageSender {

    /**
     * This method sends a message to the JMS queue which represents an email.
     *
     * @param emailRequest email to be send
     */
    void sendMessage(EmailRequest emailRequest);
}
