package com.iquestint.jms;

import com.iquestint.jms.email.EmailResponse;
import org.springframework.messaging.Message;

/**
 * @author vladu
 */
public class MessageReceiverImpl implements MessageReceiver {

    @Override
    public void processResponse(Message<EmailResponse> responseMessage) {
        System.out.println(responseMessage.getPayload());
    }
}
