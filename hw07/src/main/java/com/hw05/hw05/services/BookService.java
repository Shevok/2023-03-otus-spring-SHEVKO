package com.hw05.hw05.services;

import com.hw05.hw05.model.Book;

import java.util.List;
import java.util.Optional;

public interface BookService {

    Book addNew(Book book);

    Optional<Book> getById(long id);

    Optional<Book> getByName(String name);

    List<Book> getAll();

    void deleteByName(String name);

    Book update(Book book);
}
