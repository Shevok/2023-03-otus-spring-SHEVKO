package com.hw05.hw05.services;

import com.hw05.hw05.model.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorService {

    Author addNew(Author author);

    Optional<Author> getById(long id);

    Optional<Author> getByName(String name);

    List<Author> getAll();

    Author getOrCreateNew(Author author);
}
