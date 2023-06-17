package com.hw03.shell;

import com.hw03.services.StudentTestingService;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

@ShellComponent
public class ShellStart {

    private final StudentTestingService studentTestingService;

    public ShellStart(StudentTestingService studentTestingService) {
        this.studentTestingService = studentTestingService;
    }

    @ShellMethod(value = "start", key = {"start"})
    public void getAuthorByName() {
        studentTestingService.startTesting();
    }
}
