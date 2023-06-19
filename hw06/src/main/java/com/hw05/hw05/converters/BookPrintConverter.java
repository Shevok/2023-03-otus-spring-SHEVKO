package com.hw05.hw05.converters;

import com.hw05.hw05.model.Book;
import com.hw05.hw05.model.Comment;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class BookPrintConverter implements PrintConverter<Book> {
    @Override
    public String convertSuccess(Book book) {
        return "Id: " + book.getId() + " Имя: " + book.getName() + " Автор: "
                + book.getAuthor().getName() + " Жанр: " + book.getGenre().getName()
                + " Комментарии: " + convertCommentsToString(book.getComments());
    }

    private String convertCommentsToString(List<Comment> allComments) {
        return allComments.stream().map(Comment::getContent)
                .collect(Collectors.joining("; "));
    }
}
