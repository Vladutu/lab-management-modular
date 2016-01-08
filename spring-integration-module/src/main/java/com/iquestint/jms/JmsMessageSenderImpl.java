package com.iquestint.jms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

/**
 * @author vladu
 */
@Service
public class JmsMessageSenderImpl implements JmsMessageSender {

    @Autowired
    private JmsTemplate jmsTemplate;

    public void sendMessage(String message) {
        jmsTemplate.send(new JmsMessageCreator(message));
    }
}
