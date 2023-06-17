package com.hw05.hw05.DAO;

import com.hw05.hw05.model.Author;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("DAO для работы с авторами должен ")
@DataJpaTest
class AuthorRepositoryTest {

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private TestEntityManager em;

    @DisplayName("находить автора по имени в БД")
    @Test
    void getByName() {
        Author expectedAuthor = em.find(Author.class, 1);
        Optional<Author> actualAuthorOpt = authorRepository.findByName("Егоров");
        assertTrue(actualAuthorOpt.isPresent());
        Author actualAuthor = actualAuthorOpt.get();
        assertEquals(actualAuthor.getName(), expectedAuthor.getName());
    }
}