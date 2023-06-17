package com.hw05.hw05.services;

import com.hw05.hw05.DAO.CommentDao;
import com.hw05.hw05.model.Book;
import com.hw05.hw05.model.Comment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CommentServiceImpl implements CommentService {

    private final CommentDao commentDao;

    private final BookService bookService;

    public CommentServiceImpl(CommentDao commentDao, BookService bookService) {
        this.commentDao = commentDao;
        this.bookService = bookService;
    }

    @Transactional
    @Override
    public Comment insert(Comment comment) {
        Optional<Book> book = bookService.getById(comment.getBook().getId());
        comment.setBook(book.get());
        return commentDao.insert(comment);
    }

    @Override
    public Optional<Comment> getById(long id) {
        return commentDao.getById(id);
    }

    @Override
    public List<Comment> getAll() {
        return commentDao.getAll();
    }

    @Override
    public void delete(Comment comment) {
        commentDao.delete(comment);
    }

    @Override
    public void deleteById(long id) {
        Optional<Comment> commentToDelete = getById(id);
        commentToDelete.ifPresent(commentDao::delete);
    }

    @Transactional
    @Override
    public Comment update(Comment comment) {
        Optional<Comment> commentFromDb = getById(comment.getId());
        commentFromDb.ifPresent(value -> value.setContent(comment.getContent()));
        return commentDao.update(commentFromDb.get());
    }
}
