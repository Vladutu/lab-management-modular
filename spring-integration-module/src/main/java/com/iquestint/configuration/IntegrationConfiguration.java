package com.iquestint.configuration;

import com.iquestint.jms.JmsMessageReceiver;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jms.connection.CachingConnectionFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.listener.DefaultMessageListenerContainer;
import org.springframework.jms.listener.MessageListenerContainer;
import org.springframework.jms.listener.adapter.MessageListenerAdapter;

import javax.jms.ConnectionFactory;

/**
 * @author vladu
 */
@Configuration
@ComponentScan(basePackages = "com.iquestint")
@PropertySource(value = { "classpath:jms-config.properties" })
public class IntegrationConfiguration {

    @Autowired
    private Environment environment;

    @Bean
    ConnectionFactory connectionFactory() {
        return new CachingConnectionFactory(
            new ActiveMQConnectionFactory("tcp://" + environment.getRequiredProperty("jms.host") + ":" +
                environment.getRequiredProperty("jms.port")));
    }

    @Bean
    public JmsTemplate jmsTemplate() {
        JmsTemplate jmsTemplate = new JmsTemplate();
        jmsTemplate.setConnectionFactory(connectionFactory());
        jmsTemplate.setDefaultDestinationName(environment.getRequiredProperty("jms.response.queue"));

        return jmsTemplate;
    }

    @Bean
    @Autowired
    public MessageListenerAdapter messageListenerAdapter(JmsMessageReceiver jmsMessageReceiver) {
        return new MessageListenerAdapter(jmsMessageReceiver);
    }

//    @Bean
//    public Jaxb2Marshaller jaxb2Marshaller() {
//        Jaxb2Marshaller jaxb2Marshaller = new Jaxb2Marshaller();
//        jaxb2Marshaller.setClassesToBeBound(EmailResponse.class);
//
//        return jaxb2Marshaller;
//    }
//
//    @Bean
//    @Autowired
//    public MarshallingMessageConverter marshallingMessageConverter(Jaxb2Marshaller jaxb2Marshaller) {
//        MarshallingMessageConverter marshallingMessageConverter = new MarshallingMessageConverter();
//        marshallingMessageConverter.setMarshaller(jaxb2Marshaller);
//        marshallingMessageConverter.setUnmarshaller(jaxb2Marshaller);
//
//        return marshallingMessageConverter;
//    }

    @Bean
    @Autowired
    public MessageListenerContainer messageListenerContainer(MessageListenerAdapter messageListenerAdapter) {
        DefaultMessageListenerContainer defaultMessageListenerContainer = new DefaultMessageListenerContainer();
        defaultMessageListenerContainer.setConnectionFactory(connectionFactory());
        defaultMessageListenerContainer.setDestinationName(environment.getRequiredProperty("jms.request.queue"));
        defaultMessageListenerContainer.setConcurrency(environment.getRequiredProperty("jms.concurrency"));
        defaultMessageListenerContainer.setMessageListener(messageListenerAdapter);
        // defaultMessageListenerContainer.setMessageConverter(marshallingMessageConverter);

        return defaultMessageListenerContainer;
    }

}
