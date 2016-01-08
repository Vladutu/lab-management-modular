package com.iquestint.jms;

/**
 * @author vladu
 */
public interface JmsMessageReceiver {

    void handleMessage(String message);
}
