package com.example.makersharks.utils;

import org.springframework.util.StringUtils;

public class Util {
    public static String convertCamelToSnakeCase(String camelCase) {
        return StringUtils.uncapitalize(camelCase.replaceAll("([a-z])([A-Z]+)", "$1_$2")).toLowerCase();
    }
}
