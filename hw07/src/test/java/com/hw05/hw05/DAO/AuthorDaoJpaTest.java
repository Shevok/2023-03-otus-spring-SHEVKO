package com.hw05.hw05.DAO;

import com.hw05.hw05.model.Author;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("DAO для работы с авторами должен ")
@DataJpaTest()
@Import({AuthorDaoJpa.class})
class AuthorDaoJpaTest {

    @Autowired
    private AuthorDaoJpa authorDaoJpa;

    @Autowired
    private TestEntityManager em;

    @DisplayName("добавлять автора в БД")
    @Test
    void insert() {
        Author expectedAuthor = new Author("НовыйАвтор");
        Author actualAuthor = authorDaoJpa.insert(expectedAuthor);
        Assertions.assertThat(actualAuthor).isNotNull();
        assertEquals(actualAuthor.getName(), expectedAuthor.getName());
    }

    @DisplayName("находить автора по id в БД")
    @Test
    void getById() {
        Author expectedAuthor = em.find(Author.class, 1);
        Optional<Author> actualAuthorOpt = authorDaoJpa.getById(1);
        assertTrue(actualAuthorOpt.isPresent());
        Author actualAuthor = actualAuthorOpt.get();
        assertEquals(actualAuthor.getName(), expectedAuthor.getName());
    }

    @DisplayName("находить автора по имени в БД")
    @Test
    void getByName() {
        Author expectedAuthor = em.find(Author.class, 1);
        Optional<Author> actualAuthorOpt = authorDaoJpa.getByName("Егоров");
        assertTrue(actualAuthorOpt.isPresent());
        Author actualAuthor = actualAuthorOpt.get();
        assertEquals(actualAuthor.getName(), expectedAuthor.getName());
    }

    @DisplayName("находить всех авторов в БД")
    @Test
    void getAll() {
        List<Author> authors = authorDaoJpa.getAll();
        Assertions.assertThat(authors).isNotNull().hasSize(3);
    }
}