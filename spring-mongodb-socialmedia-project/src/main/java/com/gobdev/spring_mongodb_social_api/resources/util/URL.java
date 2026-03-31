package com.gobdev.spring_mongodb_social_api.resources.util;

import java.net.URLDecoder;
import java.time.LocalDate;

public class URL {
    public static String decodeParam(String text) {
        try {
            return URLDecoder.decode(text, "UTF-8");
        } catch (Exception e) {
            return "";
        }
    }

    public static LocalDate convertDate(String textDate, LocalDate defaultDate) {
        try {
            return LocalDate.parse(textDate);
        } catch (Exception e) {
            return defaultDate;
        }
    }
}
