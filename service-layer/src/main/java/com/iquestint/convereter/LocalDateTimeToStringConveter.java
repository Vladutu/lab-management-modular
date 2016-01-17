package com.iquestint.convereter;

import org.modelmapper.AbstractConverter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author vladu
 */
public class LocalDateTimeToStringConveter extends AbstractConverter<LocalDateTime, String> {

    private DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("EEEE, DD-MM-YYYY, HH:mm");

    @Override
    protected String convert(LocalDateTime localDateTime) {
        return localDateTime.format(dateFormatter);
    }
}
