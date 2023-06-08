package com.hw05.hw05.DAO;

import com.hw05.hw05.model.Genre;

import java.util.List;
import java.util.Optional;

public interface GenreDao {
    Genre insert(Genre genre);

    Optional<Genre> getById(long id);

    Optional<Genre> getByName(String name);

    List<Genre> getAll();
}
