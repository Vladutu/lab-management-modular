package com.iquestint.rest;

import org.springframework.messaging.Message;

/**
 * This class implements the RestMessageReceiver interface.
 *
 * @author Georgian Vladutu
 */
public class RestMessageReceiverImpl implements RestMessageReceiver {

    @Override
    public void processResponse(Message<String> responseMessage) {
        System.out.println(responseMessage.getPayload());
    }
}
