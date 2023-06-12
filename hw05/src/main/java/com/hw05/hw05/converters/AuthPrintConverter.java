package com.hw05.hw05.converters;

import com.hw05.hw05.model.Author;
import org.springframework.stereotype.Component;

@Component
public class AuthPrintConverter implements PrintConverter<Author> {

    public String convertSuccess(Author author){
        return "Id: " + author.getId() + " Имя: "+ author.getName();
    }
}
