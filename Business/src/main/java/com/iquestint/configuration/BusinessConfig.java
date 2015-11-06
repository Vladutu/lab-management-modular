package com.iquestint.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author vladu
 */
@Configuration
@EnableTransactionManagement
@ComponentScan({ "com.iquestint" })
public class BusinessConfig {
}
