package com.hw05.hw05.DAO;

import com.hw05.hw05.model.Comment;

import java.util.List;
import java.util.Optional;

public interface CommentDao {

    Comment insert(Comment comment);

    Optional<Comment> getById(long id);

    List<Comment> getAll();

    void delete(Comment comment);

    Comment update(Comment comment);
}
