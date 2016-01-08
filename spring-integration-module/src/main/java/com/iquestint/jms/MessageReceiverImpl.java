package com.iquestint.jms;

import org.springframework.messaging.Message;

/**
 * @author vladu
 */
public class MessageReceiverImpl implements MessageReceiver {

    @Override
    public void processResponse(Message<String> responseMessage) {
        System.out.println(responseMessage.getPayload());
    }
}
