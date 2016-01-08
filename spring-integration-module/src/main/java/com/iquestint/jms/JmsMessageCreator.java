package com.iquestint.jms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.MessageCreator;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import java.util.UUID;

/**
 * @author vladu
 */
public class JmsMessageCreator implements MessageCreator {

    @Autowired
    private Destination jmsResponseQueue;

    private String text;

    public JmsMessageCreator() {
    }

    public JmsMessageCreator(String text) {
        this.text = text;
    }

    @Override
    public Message createMessage(Session session) throws JMSException {
        Message message = session.createTextMessage(text);
        message.setJMSCorrelationID(UUID.randomUUID().toString());
        message.setJMSReplyTo(jmsResponseQueue);

        return message;
    }
}