package com.hw03.services;

import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
public class Formatter {

    public String formatFileNameByLocale(String fileName, Locale locale) {
        int pointIndex = fileName.indexOf(".");
        return fileName.substring(0, pointIndex) + "_" + locale + fileName.substring(pointIndex);
    }
}
