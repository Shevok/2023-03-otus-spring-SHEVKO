package com.hw05.hw05.services;

import com.hw05.hw05.DAO.BookDao;
import com.hw05.hw05.model.Author;
import com.hw05.hw05.model.Book;
import com.hw05.hw05.model.Genre;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    private final BookDao bookDao;

    private final AuthorService authorService;

    private final GenreService genreService;

    public BookServiceImpl(BookDao bookDao, AuthorService authorService, GenreService genreService) {
        this.bookDao = bookDao;
        this.authorService = authorService;
        this.genreService = genreService;
    }

    @Transactional
    @Override
    public Book addNew(Book book) {
        Book bookToCreate = initAuthorAndGenreInBook(book);
        return bookDao.insert(bookToCreate);
    }

    @Override
    public Optional<Book> getById(long id) {
        return bookDao.getById(id);
    }

    @Override
    public Optional<Book> getByName(String name) {
        return bookDao.getByName(name);
    }

    @Override
    public List<Book> getAll() {
        return bookDao.getAll();
    }

    @Override
    public void deleteByName(String name) {
        Optional<Book> book = bookDao.getByName(name);
        book.ifPresent(bookDao::delete);
    }

    @Override
    public Book update(Book book) {
        Book bookToUpdate = initAuthorAndGenreInBook(book);
        return bookDao.update(bookToUpdate);
    }

    private Book initAuthorAndGenreInBook(Book book) {
        Author author = authorService.getOrCreateNew(book.getAuthor());
        Genre genre = genreService.getOrCreateNew(book.getGenre());
        book.setAuthor(author);
        book.setGenre(genre);
        return book;
    }

}
