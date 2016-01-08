package com.iquestint.jms;

import org.springframework.stereotype.Service;

/**
 * @author vladu
 */
@Service
public class JmsMessageReceiverImpl implements JmsMessageReceiver {

    @Override
    public void handleMessage(String message) {
        System.out.println(message);
    }
}
