package com.hw05.hw05.DAO;

import com.hw05.hw05.mappers.AuthorMapper;
import com.hw05.hw05.model.Author;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class AuthorDaoJdbc implements AuthorDao {

    private final NamedParameterJdbcOperations namedParameterJdbcOperations;

    public AuthorDaoJdbc(NamedParameterJdbcOperations namedParameterJdbcOperations) {
        this.namedParameterJdbcOperations = namedParameterJdbcOperations;
    }

    @Override
    public Author insert(Author author) {
        Map<String, Object> params = Map.of("name", author.getName());
        namedParameterJdbcOperations.update("insert into Authors (name) values (:name)", params);
        return getByName(author.getName()).get();
    }

    @Override
    public Optional<Author> getById(long id) {
        Map<String, Object> params = Collections.singletonMap("id", id);
        try {
            return Optional.ofNullable(namedParameterJdbcOperations.queryForObject(
                    "select * from Authors where id = :id", params, new AuthorMapper()
            ));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }

    }

    @Override
    public Optional<Author> getByName(String name) {
        Map<String, Object> params = Collections.singletonMap("name", name);
        try {
            return Optional.ofNullable(namedParameterJdbcOperations.queryForObject(
                    "select * from Authors where name = :name", params, new AuthorMapper()
            ));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<Author> getAll() {
        return namedParameterJdbcOperations.query("select * from Authors", new AuthorMapper());
    }
}
