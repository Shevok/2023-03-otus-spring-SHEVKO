package com.hw03.services.question;

import com.hw03.configs.AppProps;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest(properties = "spring.shell.interactive.enabled=false")
class QuestionsServiceFromFileImplTest {
    @Autowired
    private AppProps props;

    @Autowired
    private QuestionsServiceFromFileImpl questionsServiceFromFileImpl;

    @Test
    void getQuestionsAndAnswers() {
        props.setFileName("questions/questions.csv");
        props.setLocale(new Locale("en"));
        assertFalse(questionsServiceFromFileImpl.getQuestionsAndAnswers().isEmpty());
    }
}