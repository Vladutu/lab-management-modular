package com.iquestint.jms;

import org.springframework.messaging.Message;

/**
 * @author vladu
 */
public interface MessageReceiver {

    void processResponse(Message<String> responseMessage);
}
