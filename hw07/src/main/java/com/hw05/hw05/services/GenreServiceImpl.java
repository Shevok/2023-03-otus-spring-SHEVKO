package com.hw05.hw05.services;

import com.hw05.hw05.DAO.GenreRepository;
import com.hw05.hw05.model.Genre;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class GenreServiceImpl implements  GenreService {

    private final GenreRepository genreRepository;

    public GenreServiceImpl(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }


    @Transactional
    @Override
    public Genre addNew(Genre genre) {
        return genreRepository.save(genre);
    }

    @Override
    public Optional<Genre> getById(long id) {
        return genreRepository.findById(id);
    }

    @Override
    public Optional<Genre> getByName(String name) {
        return genreRepository.findByName(name);
    }

    @Override
    public List<Genre> getAll() {
        return genreRepository.findAll();
    }

    @Override
    public Genre getOrCreateNew(Genre genre) {
        Optional<Genre> genreFromDb = getByName(genre.getName());
        return genreFromDb.orElseGet(() -> addNew(genre));
    }
}
