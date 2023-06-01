package com.hw03.services.question;

import com.hw03.configs.AppProps;
import com.hw03.services.ConnectionService;
import com.hw03.services.Formatter;
import com.hw03.services.ResourceService;
import com.hw03.services.utils.QuestionConverter;
import org.junit.jupiter.api.Test;

import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertFalse;

class QuestionsServiceFromFileImplTest {
    private  AppProps props = new AppProps();

    private  Formatter formatter = new Formatter();

    private  ConnectionService connectionService = new ConnectionService(props, formatter);

    private  ResourceService resourceService = new ResourceService();


    private  QuestionConverter questionConverter = new QuestionConverter();

    private QuestionsServiceFromFileImpl questionsServiceFromFileImpl = new QuestionsServiceFromFileImpl(connectionService,
            resourceService, questionConverter);


    @Test
    void getQuestionsAndAnswers() {
        props.setFileName("questions/questions.csv");
        props.setLocale(new Locale("en"));
        assertFalse(questionsServiceFromFileImpl.getQuestionsAndAnswers().isEmpty());
    }
}