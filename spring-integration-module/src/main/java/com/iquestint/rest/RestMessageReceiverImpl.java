package com.iquestint.rest;

import org.springframework.messaging.Message;

/**
 * @author vladu
 */
public class RestMessageReceiverImpl implements RestMessageReceiver {

    @Override
    public void processResponse(Message<String> responseMessage) {
        System.out.println(responseMessage.getPayload());
    }
}
