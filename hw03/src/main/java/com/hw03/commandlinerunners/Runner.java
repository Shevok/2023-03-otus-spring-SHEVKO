package com.hw03.commandlinerunners;

import com.hw03.services.StudentTestingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Runner implements CommandLineRunner {

    private final StudentTestingService studentTestingService;

    @Autowired
    public Runner(StudentTestingService studentTestingService) {
        this.studentTestingService = studentTestingService;
    }

    @Override
    public void run(String... args) throws Exception {
        studentTestingService.startTesting();
    }
}
