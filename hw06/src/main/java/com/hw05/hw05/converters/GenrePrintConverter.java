package com.hw05.hw05.converters;

import com.hw05.hw05.model.Genre;
import org.springframework.stereotype.Component;

@Component
public class GenrePrintConverter implements PrintConverter<Genre>{
    @Override
    public String convertSuccess(Genre genre) {
        return "Id: " + genre.getId() + " Имя: "+ genre.getName();
    }
}
