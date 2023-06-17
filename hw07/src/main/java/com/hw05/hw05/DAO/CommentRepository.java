package com.hw05.hw05.DAO;

import com.hw05.hw05.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
