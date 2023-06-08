package com.hw05.hw05.DAO;

import com.hw05.hw05.mappers.BookMapper;
import com.hw05.hw05.model.Book;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class BookDaoJdbc implements BookDao {
    private final NamedParameterJdbcOperations namedParameterJdbcOperations;

    public BookDaoJdbc(NamedParameterJdbcOperations namedParameterJdbcOperations) {
        this.namedParameterJdbcOperations = namedParameterJdbcOperations;
    }


    @Override
    public Book insert(Book book) {
        Map<String, Object> params = Map.of( "name", book.getName(),
                "authorID", book.getAuthor().getId(), "genreID", book.getGenre().getId());
        namedParameterJdbcOperations.update("insert into Books (name, authorID, genreID)" +
                        "values (:name, :authorID, :genreID)", params
        );
        return getByName(book.getName()).get();
    }

    @Override
    public Optional<Book> getById(long id) {
        Map<String, Object> params = Collections.singletonMap("id", id);
        try {
             return Optional.ofNullable(namedParameterJdbcOperations.queryForObject("SELECT BOOKS.ID as bId," +
                             " BOOKS.Name as bName," +
                            " AUTHORS.ID as aId, AUTHORS.name as aName, GENRES.ID as gID, GENRES.NAME as gName" +
                            " FROM BOOKS" +
                            " INNER JOIN AUTHORS  ON BOOKS.AUTHORID=AUTHORS .ID" +
                            " INNER JOIN GENRES  ON BOOKS.GENREID=GENRES.ID  " +
                            " where BOOKS.ID = :id"
                    , params, new BookMapper()
            ));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<Book> getByName(String name) {
        Map<String, Object> params = Collections.singletonMap("name", name);
        try {
            return Optional.ofNullable(namedParameterJdbcOperations.queryForObject("SELECT BOOKS.ID as bId," +
                            " BOOKS.Name as bName," +
                            " AUTHORS.ID as aId, AUTHORS.name as aName, GENRES.ID as gID, GENRES.NAME as gName" +
                            " FROM BOOKS" +
                            " INNER JOIN AUTHORS  ON BOOKS.AUTHORID=AUTHORS .ID" +
                            " INNER JOIN GENRES  ON BOOKS.GENREID=GENRES.ID  " +
                            " where BOOKS.Name = :name"
                    , params, new BookMapper()
            ));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<Book> getAll() {
        return namedParameterJdbcOperations.query("SELECT BOOKS.ID as bId, BOOKS.Name as bName," +
                " AUTHORS.ID as aId, AUTHORS.name as aName, GENRES.ID as gID, GENRES.NAME as gName" +
                " FROM BOOKS" +
                " INNER JOIN AUTHORS  ON BOOKS.AUTHORID=AUTHORS .ID" +
                " INNER JOIN GENRES  ON BOOKS.GENREID=GENRES.ID  ",
                new BookMapper());
    }

    @Override
    public void deleteByName(String name) {
        Map<String, Object> params = Collections.singletonMap("name", name);
        namedParameterJdbcOperations.update(
                "delete from Books where name = :name", params
        );
    }

    @Override
    public Book update(Book book) {
        Map<String, Object> params = Map.of("id", book.getId(), "name", book.getName(),
                "authorID", book.getAuthor().getId(), "genreID", book.getGenre().getId());
        namedParameterJdbcOperations.update("update Books set name = :name , authorID = :authorID " +
                ", genreID = :genreID where id = :id", params
        );
        return getById(book.getId()).get();
    }
}
