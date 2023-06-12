package com.hw05.hw05.DAO;

import com.hw05.hw05.model.Author;
import com.hw05.hw05.model.Book;
import com.hw05.hw05.model.Genre;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Dao для работы с книгами должно")
@JdbcTest
@Import(BookDaoJdbc.class)
class BookDaoJdbcTest {

    public static final String BOOK_NAME = "Веселые истории";

    @Autowired
    private BookDaoJdbc bookDao;

    @DisplayName("добавлять книгу в БД")
    @Test
    void shouldInsertBook() {
        Book expectedBook = initBook( "Книга");
        Book actualBook = bookDao.insert(expectedBook);
        Assertions.assertThat(actualBook).isNotNull();
        assertEquals(actualBook.getName(), expectedBook.getName());
        assertEquals(actualBook.getAuthor().getId(), expectedBook.getAuthor().getId());
        assertEquals(actualBook.getGenre().getId(), expectedBook.getGenre().getId());

    }

    @DisplayName("находить книгу по id в БД")
    @Test
    void getById() {
        Book expectedBook = initBook(BOOK_NAME);
        Optional<Book> actualBookOpt = bookDao.getById(1);
        assertTrue(actualBookOpt.isPresent());
        Book actualBook = actualBookOpt.get();
        assertEquals(actualBook.getName(), expectedBook.getName());
        assertEquals(actualBook.getAuthor().getId(), expectedBook.getAuthor().getId());
        assertEquals(actualBook.getGenre().getId(), expectedBook.getGenre().getId());
    }

    @DisplayName("находить книгу по имени в БД")
    @Test
    void getByName() {
        Book expectedBook = initBook(BOOK_NAME);
        Optional<Book> actualBookOpt = bookDao.getByName("Веселые истории");
        assertTrue(actualBookOpt.isPresent());
        Book actualBook = actualBookOpt.get();
        assertEquals(actualBook.getName(), expectedBook.getName());
        assertEquals(actualBook.getAuthor().getId(), expectedBook.getAuthor().getId());
        assertEquals(actualBook.getGenre().getId(), expectedBook.getGenre().getId());
    }

    @DisplayName("находить все книги в БД")
    @Test
    void getAll() {
        List<Book> books = bookDao.getAll();
        Assertions.assertThat(books).isNotNull().hasSize(4);
    }

    @DisplayName("удалять книгу по имени в БД")
    @Test
    void deleteByName() {
        Optional<Book> actualBookOpt = bookDao.getByName(BOOK_NAME);
        assertTrue(actualBookOpt.isPresent());
        bookDao.deleteByName(BOOK_NAME);
        Optional<Book> deletedBook = bookDao.getByName(BOOK_NAME);
        assertFalse(deletedBook.isPresent());
    }

    @DisplayName("обновлять книгу в БД")
    @Test
    void update() {
        String newBookName = "НовоеНазвание";
        Optional<Book> actualBookOpt = bookDao.getByName(BOOK_NAME);
        assertTrue(actualBookOpt.isPresent());
        Book bookToUpdate = actualBookOpt.get();
        bookToUpdate.setName(newBookName);
        Book updatedBook = bookDao.update(bookToUpdate);
        assertEquals(updatedBook.getName(), newBookName);
        assertEquals(updatedBook.getId(), bookToUpdate.getId());
    }

    private Book initBook(String bookName){
        Author author = new Author();
        Genre genre = new Genre();
        author.setId(1);
        genre.setId(1);
        return new Book( bookName, author, genre);
    }
}