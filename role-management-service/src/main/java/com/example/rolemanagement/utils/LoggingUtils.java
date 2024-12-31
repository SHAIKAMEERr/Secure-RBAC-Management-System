package com.example.rolemanagement.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggingUtils {

    public static Logger getLogger(Class<?> clazz) {
        return LoggerFactory.getLogger(clazz);
    }
}
