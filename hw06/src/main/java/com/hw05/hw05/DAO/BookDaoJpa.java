package com.hw05.hw05.DAO;

import com.hw05.hw05.model.Book;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class BookDaoJpa implements BookDao {

    @PersistenceContext
    private final EntityManager entityManager;

    public BookDaoJpa(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Book insert(Book book) {
        entityManager.persist(book);
        return getByName(book.getName()).get();
    }

    @Override
    public Optional<Book> getById(long id) {
        return Optional.ofNullable(entityManager.find(Book.class, id));
    }

    @Override
    public Optional<Book> getByName(String name) {
        TypedQuery<Book> query = entityManager.createQuery("select s " +
                        "from Book s " +
                        "where s.name = :name",
                Book.class);
        query.setParameter("name", name);
        return query.getResultList().stream().findFirst();
    }

    @Override
    public List<Book> getAll() {
        return entityManager.createQuery("SELECT b FROM Book b", Book.class).getResultList();
    }

    @Override
    public void delete(Book book) {
        entityManager.remove(book);
    }

    @Override
    public Book update(Book book) {
        return entityManager.merge(book);
    }
}
