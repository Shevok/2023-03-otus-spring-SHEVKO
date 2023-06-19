package com.hw05.hw05.DAO;

import com.hw05.hw05.model.Author;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class AuthorDaoJpa implements AuthorDao {

    @PersistenceContext
    private final EntityManager entityManager;

    public AuthorDaoJpa(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Author insert(Author author) {
        entityManager.persist(author);
        return getByName(author.getName()).get();
    }

    @Override
    public Optional<Author> getById(long id) {
        return Optional.of(entityManager.find(Author.class, id));
    }

    @Override
    public Optional<Author> getByName(String name) {
        TypedQuery<Author> query = entityManager.createQuery("select s " +
                        "from Author s " +
                        "where s.name = :name",
                Author.class);
        query.setParameter("name", name);
        return query.getResultList().stream().findFirst();
    }

    @Override
    public List<Author> getAll() {
        return entityManager.createQuery("SELECT a FROM Author a", Author.class).getResultList();
    }
}
