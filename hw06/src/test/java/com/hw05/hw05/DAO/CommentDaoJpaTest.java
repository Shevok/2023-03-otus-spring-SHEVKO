package com.hw05.hw05.DAO;

import com.hw05.hw05.model.Book;
import com.hw05.hw05.model.Comment;
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

@DisplayName("DAO для работы с комментариями должен ")
@DataJpaTest()
@Import({CommentDaoJpa.class})
class CommentDaoJpaTest {

    @Autowired
    private CommentDaoJpa commentDaoJpa;

    @Autowired
    private TestEntityManager em;

    @DisplayName("добавлять комментрий в БД")
    @Test
    void insert() {
        Comment expectedComment = new Comment("Комментарий", new Book(1));
        Comment actualComment = commentDaoJpa.insert(expectedComment);
        Assertions.assertThat(actualComment).isNotNull();
        assertEquals(actualComment.getContent(), expectedComment.getContent());
        assertEquals(actualComment.getBook().getId(), expectedComment.getBook().getId());
    }

    @DisplayName("находить комментарий по id в БД")
    @Test
    void getById() {
        Comment expectedComment = em.find(Comment.class, 1);
        Optional<Comment> actualCommentOpt = commentDaoJpa.getById(1);
        assertTrue(actualCommentOpt.isPresent());
        Comment actualComment = actualCommentOpt.get();
        assertEquals(expectedComment.getContent(), actualComment.getContent());
        assertEquals(actualComment.getBook().getId(), expectedComment.getBook().getId());
    }

    @DisplayName("находить все комментарии в БД")
    @Test
    void getAll() {
        List<Comment> comments = commentDaoJpa.getAll();
        Assertions.assertThat(comments).isNotNull().hasSize(4);
    }

    @DisplayName("удалять комментарий из БД")
    @Test
    void delete() {
        Optional<Comment> actualCommentOpt = commentDaoJpa.getById(1);
        assertTrue(actualCommentOpt.isPresent());
        commentDaoJpa.delete(actualCommentOpt.get());
        Optional<Comment> deletedComment = commentDaoJpa.getById(1);
        assertFalse(deletedComment.isPresent());
    }

    @DisplayName("обновлять комментарий в БД")
    @Test
    void update() {
        String newComment = "НовыйКомментарий";
        Optional<Comment> actualCommentOpt = commentDaoJpa.getById(1);
        assertTrue(actualCommentOpt.isPresent());
        Comment commentToUpdate = actualCommentOpt.get();
        commentToUpdate.setContent(newComment);
        Comment updatedComment = commentDaoJpa.update(commentToUpdate);
        assertEquals(updatedComment.getContent(), newComment);
        assertEquals(updatedComment.getId(), commentToUpdate.getId());
    }
}