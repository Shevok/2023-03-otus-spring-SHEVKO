package com.hw05.hw05.services;

import com.hw05.hw05.DAO.GenreDao;
import com.hw05.hw05.model.Genre;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GenreServiceImpl implements  GenreService {

    private final GenreDao genreDao;

    public GenreServiceImpl(GenreDao genreDao) {
        this.genreDao = genreDao;
    }

    @Override
    public Genre addNew(Genre genre) {
        return genreDao.insert(genre);
    }

    @Override
    public Optional<Genre> getById(long id) {
        return genreDao.getById(id);
    }

    @Override
    public Optional<Genre> getByName(String name) {
        return genreDao.getByName(name);
    }

    @Override
    public List<Genre> getAll() {
        return genreDao.getAll();
    }

    @Override
    public void showAll() {
        System.out.println(getAll());
    }

    @Override
    public Genre getOrCreateNew(Genre genre) {
        Optional<Genre> genreFromDb = getByName(genre.getName());
        return genreFromDb.orElseGet(() -> addNew(genre));
    }
}
