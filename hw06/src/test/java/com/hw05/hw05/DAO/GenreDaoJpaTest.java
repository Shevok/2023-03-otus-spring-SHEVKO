package com.hw05.hw05.DAO;

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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("DAO для работы с жанрами должен ")
@DataJpaTest()
@Import({GenreDaoJpa.class})
class GenreDaoJpaTest {

    @Autowired
    private GenreDaoJpa genreDaoJpa;

    @Autowired
    private TestEntityManager em;

    @DisplayName("добавлять жанр в БД")
    @Test
    void insert() {
        Genre expectedGenre = new Genre("Новыйжанр");
        Genre actualGenre = genreDaoJpa.insert(expectedGenre);
        Assertions.assertThat(actualGenre).isNotNull();
        assertEquals(expectedGenre.getName(), actualGenre.getName());
    }

    @DisplayName("находить жанр по id в БД")
    @Test
    void getById() {
        Genre expectedGenre = em.find(Genre.class, 1);
        Optional<Genre> actualGenreOpt = genreDaoJpa.getById(1);
        assertTrue(actualGenreOpt.isPresent());
        Genre actualGenre = actualGenreOpt.get();
        assertEquals(expectedGenre.getName(), actualGenre.getName());
    }

    @DisplayName("находить жанр по имени в БД")
    @Test
    void getByName() {
        Genre expectedAuthor = em.find(Genre.class, 1);
        Optional<Genre> actualGenreOpt = genreDaoJpa.getByName("Комедия");
        assertTrue(actualGenreOpt.isPresent());
        Genre actualGenre = actualGenreOpt.get();
        assertEquals(expectedAuthor.getName(), actualGenre.getName());
    }

    @DisplayName("находить все жанры в БД")
    @Test
    void getAll() {
        List<Genre> genres = genreDaoJpa.getAll();
        Assertions.assertThat(genres).isNotNull().hasSize(3);
    }

}