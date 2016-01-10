package com.iquestint.jms;

import com.iquestint.jms.email.EmailResponse;
import org.springframework.messaging.Message;

/**
 * @author vladu
 */
public interface MessageReceiver {

    void processResponse(Message<EmailResponse> responseMessage);
}
