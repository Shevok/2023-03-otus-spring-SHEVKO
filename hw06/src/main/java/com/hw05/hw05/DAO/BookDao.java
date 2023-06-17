package com.hw05.hw05.DAO;

import com.hw05.hw05.model.Book;

import java.util.List;
import java.util.Optional;

public interface BookDao {

    Book insert(Book book);

    Optional<Book> getById(long id);

    Optional<Book> getByName(String name);

    List<Book> getAll();

    void delete(Book book);

    Book update(Book book);
}
