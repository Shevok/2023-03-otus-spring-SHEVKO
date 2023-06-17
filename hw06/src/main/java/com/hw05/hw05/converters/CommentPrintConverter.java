package com.hw05.hw05.converters;

import com.hw05.hw05.model.Comment;
import org.springframework.stereotype.Component;

@Component
public class CommentPrintConverter implements PrintConverter<Comment> {
    @Override
    public String convertSuccess(Comment comment) {
        return "Id: " + comment.getId() + " Содержание: " + comment.getContent() +
                " Книга: " + comment.getBook().getName();
    }
}
