package com.solvd.bankingservice.utils;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * @author Vadym Spitsyn
 * @created 2025-02-04
 */
public class LocalDateAdapter extends XmlAdapter<String, LocalDate> {

    // Formatter for date in "yyyy-MM-dd" format
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @Override
    public LocalDate unmarshal(String v) throws Exception {
        return LocalDate.parse(v, DATE_FORMATTER); // Converts string to LocalDate
    }

    @Override
    public String marshal(LocalDate v) throws Exception {
        return DATE_FORMATTER.format(v); // Converts LocalDate to string
    }
}