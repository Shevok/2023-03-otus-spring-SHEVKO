package com.hw05.hw05.services;

import com.hw05.hw05.model.Comment;

import java.util.List;
import java.util.Optional;

public interface CommentService {

    Comment insert(Comment comment);

    Optional<Comment> getById(long id);

    List<Comment> getAll();

    void delete(Comment comment);

    void deleteById(long id);

    Comment update(Comment comment);

}
