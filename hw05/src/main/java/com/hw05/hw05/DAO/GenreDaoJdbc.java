package com.hw05.hw05.DAO;

import com.hw05.hw05.mappers.GenreMapper;
import com.hw05.hw05.model.Genre;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class GenreDaoJdbc implements GenreDao {

    private final NamedParameterJdbcOperations namedParameterJdbcOperations;

    public GenreDaoJdbc(NamedParameterJdbcOperations namedParameterJdbcOperations) {
        this.namedParameterJdbcOperations = namedParameterJdbcOperations;
    }

    @Override
    public Genre insert(Genre genre) {
        Map<String, Object> params = Map.of( "name", genre.getName());
        namedParameterJdbcOperations.update("insert into Genres (name) values (:name)", params);
        return getByName(genre.getName()).get();
    }

    @Override
    public Optional<Genre> getById(long id) {
        Map<String, Object> params = Collections.singletonMap("id", id);
        try {
            return Optional.ofNullable(namedParameterJdbcOperations.queryForObject(
                    "select id, name from Genres where id = :id", params, new GenreMapper()
            ));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<Genre> getByName(String name) {
        Map<String, Object> params = Collections.singletonMap("name", name);
        try {
            return Optional.ofNullable(namedParameterJdbcOperations.queryForObject(
                    "select id, name from Genres where name = :name", params, new GenreMapper()
            ));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<Genre> getAll() {
        return namedParameterJdbcOperations.query("select id, name from Genres", new GenreMapper());
    }
}
