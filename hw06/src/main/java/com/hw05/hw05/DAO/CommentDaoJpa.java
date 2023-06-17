package com.hw05.hw05.DAO;

import com.hw05.hw05.model.Comment;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class CommentDaoJpa implements CommentDao {

    @PersistenceContext
    private final EntityManager entityManager;

    public CommentDaoJpa(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Comment insert(Comment comment) {
        entityManager.persist(comment);
        return comment;
    }

    @Override
    public Optional<Comment> getById(long id) {
        return Optional.ofNullable(entityManager.find(Comment.class, id));
    }

    @Override
    public List<Comment> getAll() {
        return entityManager.createQuery("SELECT c FROM Comment c", Comment.class).getResultList();
    }

    @Override
    public void delete(Comment comment) {
        entityManager.remove(comment);
    }

    @Override
    public Comment update(Comment comment) {
        return entityManager.merge(comment);
    }
}
