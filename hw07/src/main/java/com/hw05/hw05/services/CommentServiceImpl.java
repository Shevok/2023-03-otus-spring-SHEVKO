package com.hw05.hw05.services;

import com.hw05.hw05.DAO.CommentRepository;
import com.hw05.hw05.model.Book;
import com.hw05.hw05.model.Comment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;

    private final BookService bookService;

    public CommentServiceImpl(CommentRepository commentRepository, BookService bookService) {
        this.commentRepository = commentRepository;
        this.bookService = bookService;
    }

    @Transactional
    @Override
    public Comment insert(Comment comment) {
        Optional<Book> book = bookService.getById(comment.getBook().getId());
        comment.setBook(book.get());
        return commentRepository.save(comment);
    }

    @Override
    public Optional<Comment> getById(long id) {
        return commentRepository.findById(id);
    }

    @Override
    public List<Comment> getAll() {
        return commentRepository.findAll();
    }

    @Override
    public void delete(Comment comment) {
        commentRepository.delete(comment);
    }

    @Override
    public void deleteById(long id) {
        Optional<Comment> commentToDelete = getById(id);
        commentToDelete.ifPresent(commentRepository::delete);
    }

    @Transactional
    @Override
    public Comment update(Comment comment) {
        Optional<Comment> commentFromDb = getById(comment.getId());
        commentFromDb.ifPresent(value -> value.setContent(comment.getContent()));
        return commentRepository.save(commentFromDb.get());
    }
}
