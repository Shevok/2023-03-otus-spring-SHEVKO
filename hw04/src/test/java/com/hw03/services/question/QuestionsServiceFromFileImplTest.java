package com.hw03.services.question;

import com.hw03.configs.AppProps;
import com.hw03.services.ConnectionService;
import com.hw03.services.Formatter;
import com.hw03.services.ResourceService;
import com.hw03.services.utils.QuestionConverter;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest
class QuestionsServiceFromFileImplTest {
    @Autowired
    private  AppProps props;

    @Autowired
    private  Formatter formatter;

    @Autowired
    private  ConnectionService connectionService;

    @Autowired
    private  ResourceService resourceService;

    @Autowired
    private  QuestionConverter questionConverter;

    @Autowired
    private QuestionsServiceFromFileImpl questionsServiceFromFileImpl;

    @Test
    void getQuestionsAndAnswers() {
        props.setFileName("questions/questions.csv");
        props.setLocale(new Locale("en"));
        assertFalse(questionsServiceFromFileImpl.getQuestionsAndAnswers().isEmpty());
    }
}