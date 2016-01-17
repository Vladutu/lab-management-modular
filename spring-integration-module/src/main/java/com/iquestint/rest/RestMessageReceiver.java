package com.iquestint.rest;

import org.springframework.messaging.Message;

/**
 * This class is used to receive a REST message.
 *
 * @author Georgian Vladutu
 */
public interface RestMessageReceiver {

    /**
     * This method receives the REST message which represents a list of users.
     *
     * @param responseMessage the list of users
     */
    void processResponse(Message<String> responseMessage);
}
