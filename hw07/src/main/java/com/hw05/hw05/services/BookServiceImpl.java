package com.hw05.hw05.services;

import com.hw05.hw05.DAO.BookRepository;
import com.hw05.hw05.model.Author;
import com.hw05.hw05.model.Book;
import com.hw05.hw05.model.Genre;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    private final AuthorService authorService;

    private final GenreService genreService;

    public BookServiceImpl(BookRepository bookRepository, AuthorService authorService, GenreService genreService) {
        this.bookRepository = bookRepository;
        this.authorService = authorService;
        this.genreService = genreService;
    }

    @Transactional
    @Override
    public Book addNew(Book book) {
        Book bookToCreate = initAuthorAndGenreInBook(book);
        return bookRepository.save(bookToCreate);
    }

    @Override
    public Optional<Book> getById(long id) {
        return bookRepository.findById(id);
    }

    @Override
    public Optional<Book> getByName(String name) {
        return bookRepository.findByName(name);
    }

    @Override
    public List<Book> getAll() {
        return bookRepository.findAll();
    }

    @Override
    public void deleteByName(String name) {
        Optional<Book> book = bookRepository.findByName(name);
        book.ifPresent(bookRepository::delete);
    }

    @Override
    public Book update(Book book) {
        Book bookToUpdate = initAuthorAndGenreInBook(book);
        return bookRepository.save(bookToUpdate);
    }

    private Book initAuthorAndGenreInBook(Book book) {
        Author author = authorService.getOrCreateNew(book.getAuthor());
        Genre genre = genreService.getOrCreateNew(book.getGenre());
        book.setAuthor(author);
        book.setGenre(genre);
        return book;
    }

}
