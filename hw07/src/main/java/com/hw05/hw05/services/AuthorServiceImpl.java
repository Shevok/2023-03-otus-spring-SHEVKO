package com.hw05.hw05.services;

import com.hw05.hw05.DAO.AuthorRepository;
import com.hw05.hw05.model.Author;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }


    @Transactional
    @Override
    public Author addNew(Author author) {
        return authorRepository.save(author);
    }

    @Override
    public Optional<Author> getById(long id) {
        return authorRepository.findById(id);
    }

    @Override
    public Optional<Author> getByName(String name) {
        return authorRepository.findByName(name);
    }

    @Override
    public List<Author> getAll() {
        return authorRepository.findAll();
    }

    @Override
    public Author getOrCreateNew(Author author) {
        Optional<Author> authorFomDB = authorRepository.findByName(author.getName());
        return authorFomDB.orElseGet(() -> addNew(author));
    }
}
