package com.hw05.hw05.DAO;

import com.hw05.hw05.model.Genre;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class GenreDaoJpa implements GenreDao {

    @PersistenceContext
    private final EntityManager entityManager;

    public GenreDaoJpa(EntityManager entityManager) {
        this.entityManager = entityManager;
    }


    @Override
    public Genre insert(Genre genre) {
        entityManager.persist(genre);
        return getByName(genre.getName()).get();
    }

    @Override
    public Optional<Genre> getById(long id) {
        return Optional.of(entityManager.find(Genre.class, id));
    }

    @Override
    public Optional<Genre> getByName(String name) {
        TypedQuery<Genre> query = entityManager.createQuery("select s " +
                        "from Genre s " +
                        "where s.name = :name",
                Genre.class);
        query.setParameter("name", name);
        return query.getResultList().stream().findFirst();
    }

    @Override
    public List<Genre> getAll() {
        return entityManager.createQuery("SELECT g FROM Genre g", Genre.class).getResultList();
    }
}
