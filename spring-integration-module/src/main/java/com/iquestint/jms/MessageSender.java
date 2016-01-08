package com.iquestint.jms;

import com.iquestint.jms.email.EmailRequest;

/**
 * @author vladu
 */
public interface MessageSender {

    void sendMessage(EmailRequest emailRequest);
}
