package com.hw05.hw05.shell;

import com.hw05.hw05.converters.PrintConverter;
import com.hw05.hw05.model.Genre;
import com.hw05.hw05.services.GenreService;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import java.util.List;
import java.util.Optional;

@ShellComponent
public class GenreShell {

    private final GenreService genreService;

    private final ShellHelper shellHelper;

    private final PrintConverter<Genre> genrePrintConverter;

    private final ShellOutput shellOutput;

    public GenreShell(GenreService genreService, ShellHelper shellHelper, PrintConverter<Genre> genrePrintConverter,
                      ShellOutput shellOutput) {
        this.genreService = genreService;
        this.shellHelper = shellHelper;
        this.genrePrintConverter = genrePrintConverter;
        this.shellOutput = shellOutput;
    }


    @ShellMethod(value = "get genre", key = {"g g", "get genre"})
    public void getGenreByName(@ShellOption(value = {"-n"})String name) {
        Optional<Genre> genre = genreService.getByName(name);
        shellHelper.validateOptionalObjAndShowResult(genre, genrePrintConverter);
    }

    @ShellMethod(value = "get all genres", key = {"l g", "list genres"})
    public void getAllGenres() {
        List<Genre> genres = genreService.getAll();
        shellHelper.validateObjColAndShowResult(genres, genrePrintConverter);
    }

    @ShellMethod(value = "add genre", key = {"a g", "add genre"})
    public void addGenreByName(@ShellOption(value = {"-n"})String name) {
        Genre genre = genreService.addNew(new Genre(name));
        shellOutput.success(genre, genrePrintConverter);
    }
}
