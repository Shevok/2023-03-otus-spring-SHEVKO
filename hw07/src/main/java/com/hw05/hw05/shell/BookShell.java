package com.hw05.hw05.shell;

import com.hw05.hw05.converters.PrintConverter;
import com.hw05.hw05.model.Author;
import com.hw05.hw05.model.Book;
import com.hw05.hw05.model.Genre;
import com.hw05.hw05.services.BookService;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import java.util.List;
import java.util.Optional;

@ShellComponent
public class BookShell {

    private final BookService bookService;

    private final ShellHelper shellHelper;

    private final PrintConverter<Book> bookPrintConverter;

    private final ShellOutput shellOutput;

    public BookShell(BookService bookService, ShellHelper shellHelper, PrintConverter<Book> bookPrintConverter,
                     ShellOutput shellOutput) {
        this.bookService = bookService;
        this.shellHelper = shellHelper;
        this.bookPrintConverter = bookPrintConverter;
        this.shellOutput = shellOutput;
    }

    @ShellMethod(value = "get book", key = {"g b", "get book"})
    public void getBookByName(@ShellOption(value = {"-n"})String name) {
        Optional<Book> book = bookService.getByName(name);
        shellHelper.validateOptionalObjAndShowResult(book,bookPrintConverter);
    }

    @ShellMethod(value = "get all book", key = {"l b", "list book"})
    public void getAllBooks() {
        List<Book> books = bookService.getAll();
        shellHelper.validateObjColAndShowResult(books, bookPrintConverter);
    }

    @ShellMethod(value = "add book", key = {"a b", "add book"})
    public void addBook(@ShellOption(value = {"--nb"})String bookName, @ShellOption(value = {"--na"})String authorName,
                        @ShellOption(value = {"--ng"})String genreName) {
        Book bookToCreate = new Book(bookName, new Author(authorName), new Genre(genreName));
        Book createdBook = bookService.addNew(bookToCreate);
        shellOutput.success(createdBook, bookPrintConverter);
    }

    @ShellMethod(value = "delete book", key = {"d b", "delete book"})
    public void deleteBookByName(@ShellOption(value = {"-n"})String name) {
        bookService.deleteByName(name);
        List<Book> books = bookService.getAll();
        shellHelper.validateObjColAndShowResult(books, bookPrintConverter);
    }

    @ShellMethod(value = "update book", key = {"u b", "update book"})
    public void updateBook(@ShellOption(value = {"--idb"})Long bookId, @ShellOption(value = {"--nb"})String bookName,
                           @ShellOption(value = {"--na"})String authorName,
                           @ShellOption(value = {"--ng"})String genreName) {
        Book bookToUpdate = new Book(bookId, bookName, new Author(authorName), new Genre(genreName));
        Book updatedBook = bookService.update(bookToUpdate);
        shellOutput.success(updatedBook, bookPrintConverter);
    }
}
