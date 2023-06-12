package com.hw05.hw05.shell;

import com.hw05.hw05.converters.PrintConverter;
import com.hw05.hw05.model.Author;
import com.hw05.hw05.services.AuthorService;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import java.util.List;
import java.util.Optional;

@ShellComponent
public class AuthorShell {
    private final AuthorService authorService;

    private final ShellHelper shellHelper;

    private final PrintConverter<Author> authPrintConverter;

    private final ShellOutput shellOutput;

    public AuthorShell(AuthorService authorService, ShellHelper shellHelper, PrintConverter<Author> authPrintConverter,
                       ShellOutput shellOutput) {
        this.authorService = authorService;
        this.shellHelper = shellHelper;
        this.authPrintConverter = authPrintConverter;
        this.shellOutput = shellOutput;
    }

    @ShellMethod(value = "get author", key = {"g a", "get author"})
    public void getAuthorByName(@ShellOption(value = {"-n"})String name) {
        Optional<Author> author = authorService.getByName(name);
        shellHelper.validateOptionalObjAndShowResult(author,authPrintConverter);
    }

    @ShellMethod(value = "get all author", key = {"l a", "list author"})
    public void getAllAuthors() {
        List<Author> authors = authorService.getAll();
        shellHelper.validateObjColAndShowResult(authors, authPrintConverter);
    }

    @ShellMethod(value = "add author", key = {"a a", "add author"})
    public void addAuthorByName(@ShellOption(value = {"-n"})String name) {
        Author author = authorService.addNew(new Author(name));
        shellOutput.success(author, authPrintConverter);
    }
}
