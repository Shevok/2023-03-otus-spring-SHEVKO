package com.hw03.services.question;

import com.hw03.model.Answer;
import com.hw03.model.Question;
import com.hw03.services.ConnectionService;
import com.hw03.services.ResourceService;
import com.hw03.services.utils.QuestionConverter;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

@Service
public class QuestionsServiceFromFileImpl implements QuestionsService {
    private final ConnectionService connectionService;

    private final ResourceService resourceService;

    private final QuestionConverter questionConverter;

    public QuestionsServiceFromFileImpl(ConnectionService connectionService, ResourceService resourceService,
                                        QuestionConverter questionConverter) {
        this.connectionService = connectionService;
        this.resourceService = resourceService;
        this.questionConverter = questionConverter;
    }

    @Override
    public Map<Question, List<Answer>> getQuestionsAndAnswers() {
        String fileName = connectionService.getFileName();
        InputStream is = resourceService.getFileFromResourceAsStream(fileName);
        return questionConverter.transformInputStreamToQuestionsMap(is);
    }
}

