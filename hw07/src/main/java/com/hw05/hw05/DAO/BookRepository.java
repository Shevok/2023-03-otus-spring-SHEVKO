package com.hw05.hw05.DAO;

import com.hw05.hw05.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {

    Optional<Book> findByName(String name);
}
