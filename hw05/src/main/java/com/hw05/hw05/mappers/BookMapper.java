package com.hw05.hw05.mappers;

import com.hw05.hw05.model.Author;
import com.hw05.hw05.model.Book;
import com.hw05.hw05.model.Genre;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BookMapper implements RowMapper<Book> {
    @Override
    public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
        long bookId = rs.getLong("bId");
        String bookName = rs.getString("bName");
        Author author = new Author(rs.getLong("aId"), rs.getString("aName") );
        Genre genre = new Genre(rs.getLong("gID"), rs.getString("gName") );
        return new Book(bookId, bookName, author, genre);
    }
}
