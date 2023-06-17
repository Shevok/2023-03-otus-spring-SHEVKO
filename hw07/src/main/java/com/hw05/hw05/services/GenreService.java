package com.hw05.hw05.services;

import com.hw05.hw05.model.Genre;

import java.util.List;
import java.util.Optional;

public interface GenreService {

    Genre addNew(Genre genre);

    Optional<Genre> getById(long id);

    Optional<Genre> getByName(String name);

    List<Genre> getAll();

    Genre getOrCreateNew(Genre genre);
}
