package com.iquestint.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * @author vladu
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.iquestint")
public class RestConfiguration {
}
