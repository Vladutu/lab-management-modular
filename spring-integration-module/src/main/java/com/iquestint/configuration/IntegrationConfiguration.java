package com.iquestint.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;

/**
 * @author vladu
 */
@Configuration
@ComponentScan(basePackages = "com.iquestint")
@PropertySource(value = { "classpath:jms-config.properties" })
@ImportResource({ "classpath:jms-int-spring-config.xml", "classpath:rest-int-spring-config.xml" })
public class IntegrationConfiguration {

}
