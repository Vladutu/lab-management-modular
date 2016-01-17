package com.iquestint.jms;

import com.iquestint.jms.email.EmailResponse;
import org.springframework.messaging.Message;

/**
 * This class implements the JmsMessageReceiver interface.
 *
 * @author Georgian Vladutu
 */
public class JmsMessageReceiverImpl implements JmsMessageReceiver {

    @Override
    public void processResponse(Message<EmailResponse> responseMessage) {
        System.out.println(responseMessage.getPayload());
    }
}
