package com.iquestint.rest;

import org.springframework.messaging.Message;

/**
 * @author vladu
 */
public interface RestMessageReceiver {

    void processResponse(Message<String> responseMessage);
}
