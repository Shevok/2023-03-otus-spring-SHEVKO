package com.hw05.hw05.converters;

import com.hw05.hw05.model.Book;
import org.springframework.stereotype.Component;

@Component
public class BookPrintConverter implements PrintConverter<Book>{
    @Override
    public String convertSuccess(Book book) {
        return "Id: " + book.getId() + " Имя: "+ book.getName() + " Автор: "
                + book.getAuthor().getName() + " Жанр: " + book.getGenre().getName();
    }
}
