package com.iquestint.jms;

import com.iquestint.jms.email.EmailRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.jms.JmsHeaders;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * This class implements the JmsMessageSender interface.
 *
 * @author Georgian Vladutu
 */
@Service
public class JmsMessageSenderImpl implements JmsMessageSender {

    @Autowired
    private MessageChannel emailRequestChannel;

    public void sendMessage(EmailRequest emailRequest) {
        GenericMessage<EmailRequest> genericMessage = new GenericMessage<EmailRequest>(emailRequest,
            buildResponseHeaders(
                UUID.randomUUID().toString()));
        emailRequestChannel.send(genericMessage);
    }

    private Map<String, Object> buildResponseHeaders(String correlationId) {
        Map<String, Object> responseHeaders = new HashMap<String, Object>();
        responseHeaders.put(JmsHeaders.CORRELATION_ID, correlationId);
        return responseHeaders;
    }
}
