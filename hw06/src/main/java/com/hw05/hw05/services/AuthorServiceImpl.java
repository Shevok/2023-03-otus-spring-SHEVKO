package com.hw05.hw05.services;

import com.hw05.hw05.DAO.AuthorDao;
import com.hw05.hw05.model.Author;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorDao authorDao;

    public AuthorServiceImpl(AuthorDao authorDao) {

        this.authorDao = authorDao;
    }

    @Override
    public Author addNew(Author author) {
        return authorDao.insert(author);
    }

    @Override
    public Optional<Author> getById(long id) {
        return authorDao.getById(id);
    }

    @Override
    public Optional<Author> getByName(String name) {
        return authorDao.getByName(name);
    }

    @Override
    public List<Author> getAll() {
        return authorDao.getAll();
    }

    @Override
    public void showAll() {
        System.out.println(getAll());
    }

    @Override
    public Author getOrCreateNew(Author author) {
        Optional<Author> authorFomDB = authorDao.getByName(author.getName());
        return authorFomDB.orElseGet(() -> addNew(author));
    }
}
