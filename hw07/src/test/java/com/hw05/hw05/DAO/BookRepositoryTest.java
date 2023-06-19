package com.hw05.hw05.DAO;

import com.hw05.hw05.model.Book;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("DAO для работы с книгами должен ")
@DataJpaTest
class BookRepositoryTest {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private TestEntityManager em;

    public static final String BOOK_NAME = "Веселые истории";

    @DisplayName("находить книгу по имени в БД")
    @Test
    void findByName() {
        Book expectedBook = em.find(Book.class, 1);
        Optional<Book> actualBookOpt = bookRepository.findByName(BOOK_NAME);
        assertTrue(actualBookOpt.isPresent());
        Book actualBook = actualBookOpt.get();
        assertEquals(actualBook.getName(), expectedBook.getName());
        assertEquals(actualBook.getAuthor().getId(), expectedBook.getAuthor().getId());
        assertEquals(actualBook.getGenre().getId(), expectedBook.getGenre().getId());
    }
}