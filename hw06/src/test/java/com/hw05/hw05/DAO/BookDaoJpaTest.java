package com.hw05.hw05.DAO;

import com.hw05.hw05.model.Author;
import com.hw05.hw05.model.Book;
import com.hw05.hw05.model.Genre;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("DAO для работы с книгами должен ")
@DataJpaTest()
@Import({BookDaoJpa.class})
class BookDaoJpaTest {

    public static final String BOOK_NAME = "Веселые истории";

    @Autowired
    private BookDaoJpa bookDaoJpa;

    @Autowired
    private TestEntityManager em;

    @DisplayName("добавлять книгу в БД")
    @Test
    void insert() {
        Author author = em.find(Author.class, 1L);
        Genre genre = em.find(Genre.class, 1L);
        Book expectedBook = new Book("Книга", author, genre);
        Book actualBook = bookDaoJpa.insert(expectedBook);
        Assertions.assertThat(actualBook).isNotNull();
        assertEquals(actualBook.getName(), expectedBook.getName());
        assertEquals(actualBook.getAuthor().getId(), expectedBook.getAuthor().getId());
        assertEquals(actualBook.getGenre().getId(), expectedBook.getGenre().getId());
    }

    @DisplayName("находить книгу по id в БД")
    @Test
    void getById() {
        Book expectedBook = em.find(Book.class, 1);
        Optional<Book> actualBookOpt = bookDaoJpa.getById(1);
        assertTrue(actualBookOpt.isPresent());
        Book actualBook = actualBookOpt.get();
        assertEquals(actualBook.getName(), expectedBook.getName());
        assertEquals(actualBook.getAuthor().getId(), expectedBook.getAuthor().getId());
        assertEquals(actualBook.getGenre().getId(), expectedBook.getGenre().getId());
    }

    @DisplayName("находить книгу по имени в БД")
    @Test
    void getByName() {
        Book expectedBook = em.find(Book.class, 1);
        Optional<Book> actualBookOpt = bookDaoJpa.getByName(BOOK_NAME);
        assertTrue(actualBookOpt.isPresent());
        Book actualBook = actualBookOpt.get();
        assertEquals(actualBook.getName(), expectedBook.getName());
        assertEquals(actualBook.getAuthor().getId(), expectedBook.getAuthor().getId());
        assertEquals(actualBook.getGenre().getId(), expectedBook.getGenre().getId());
    }

    @DisplayName("находить все книги в БД")
    @Test
    void getAll() {
        List<Book> books = bookDaoJpa.getAll();
        Assertions.assertThat(books).isNotNull().hasSize(4);
    }

    @DisplayName("удалять книгу из БД")
    @Test
    void delete() {
        Optional<Book> actualBookOpt = bookDaoJpa.getById(1);
        assertTrue(actualBookOpt.isPresent());
        bookDaoJpa.delete(actualBookOpt.get());
        Optional<Book> deletedBook = bookDaoJpa.getById(1);
        assertFalse(deletedBook.isPresent());
    }

    @DisplayName("обновлять книгу в БД")
    @Test
    void update() {
        String newBookName = "НовоеНазвание";
        Optional<Book> actualBookOpt = bookDaoJpa.getByName(BOOK_NAME);
        assertTrue(actualBookOpt.isPresent());
        Book bookToUpdate = actualBookOpt.get();
        bookToUpdate.setName(newBookName);
        Book updatedBook = bookDaoJpa.update(bookToUpdate);
        assertEquals(updatedBook.getName(), newBookName);
        assertEquals(updatedBook.getId(), bookToUpdate.getId());
    }
}