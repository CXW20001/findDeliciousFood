package com.cxw.finddeliciousfood.utils;

import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

@Slf4j
public class DateUtil {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy hh:mm:ss a", Locale.US);

    public static LocalDateTime formatUsDate(String usDateString) {
        LocalDateTime localDateTime;
        try {
            localDateTime = LocalDateTime.from(formatter.parse(usDateString));
        } catch (Exception e) {
            log.error("[DateUtil] formatUsDate error. usDateString=" + usDateString);
            throw new RuntimeException(e);
        }
        return localDateTime;
    }

}
