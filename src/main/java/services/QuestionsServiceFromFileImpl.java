package services;

import model.Answer;
import model.Question;
import services.utils.QuestionConverter;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

public class QuestionsServiceFromFileImpl implements QuestionsService {

    private ConnectionService connectionService;

    private ResourceService resourceService;

    private QuestionConverter questionConverter;

    public QuestionsServiceFromFileImpl(ConnectionService connectionService, ResourceService resourceService,
                                        QuestionConverter questionConverter) {
        this.connectionService = connectionService;
        this.resourceService = resourceService;
        this.questionConverter = questionConverter;
    }

    @Override
    public Map<Question, List<Answer>> getQuestions() {
        String fileName = connectionService.getFileName();
        InputStream is = resourceService.getFileFromResourceAsStream(fileName);
        return questionConverter.transformInputStreamToQuestionsMap(is);
    }
}

