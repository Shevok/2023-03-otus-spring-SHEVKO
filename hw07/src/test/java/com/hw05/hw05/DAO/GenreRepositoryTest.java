package com.hw05.hw05.DAO;

import com.hw05.hw05.model.Genre;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("DAO для работы с жанрами должен ")
@DataJpaTest
class GenreRepositoryTest {

    @Autowired
    private GenreRepository genreRepository;

    @Autowired
    private TestEntityManager em;

    public static final String GENRE_NAME = "Комедия";

    @DisplayName("находить жанр по имени в БД")
    @Test
    void findByName() {
        Genre expectedAuthor = em.find(Genre.class, 1);
        Optional<Genre> actualGenreOpt = genreRepository.findByName(GENRE_NAME);
        assertTrue(actualGenreOpt.isPresent());
        Genre actualGenre = actualGenreOpt.get();
        assertEquals(expectedAuthor.getName(), actualGenre.getName());
    }
}