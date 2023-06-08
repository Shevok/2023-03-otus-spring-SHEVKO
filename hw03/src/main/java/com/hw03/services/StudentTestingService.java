package com.hw03.services;

import com.hw03.configs.AppProps;
import com.hw03.model.Answer;
import com.hw03.model.Question;
import com.hw03.model.User;
import com.hw03.services.auth.AuthService;
import com.hw03.services.io.IOService;
import com.hw03.services.question.QuestionsService;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class StudentTestingService {
    private final QuestionsService questionsService;

    private final IOService ioService;

    private final AuthService authService;

    private final MessageSource messageSource;

    private final AppProps props;


    public StudentTestingService(QuestionsService questionsService, IOService ioService,
                                 AuthService authService, MessageSource messageSource, AppProps props) {
        this.questionsService = questionsService;
        this.ioService = ioService;
        this.authService = authService;
        this.messageSource = messageSource;
        this.props = props;
    }

    public void startTesting() {
        User student = authService.login();
        List<Optional<Answer>> studentAnswers = studentTesting();
        resultChecking(student, studentAnswers);
    }

    private List<Optional<Answer>> studentTesting() {
        Map<Question, List<Answer>> questionsAndAnswers = questionsService.getQuestionsAndAnswers();
        return askQuestionsAndGetAnswers(questionsAndAnswers);
    }

    private List<Optional<Answer>> askQuestionsAndGetAnswers(Map<Question, List<Answer>> questionsAndAnswers) {
        List<Optional<Answer>> studentAnswers = new ArrayList<>();
        for (var questionAndAnswers : questionsAndAnswers.entrySet()) {
            String question = questionAndAnswers.getKey().getValue();
            List<Answer> answers = questionAndAnswers.getValue();
            Optional<Answer> studentAnswer = askQuestionAndGetAnswer(question, answers);
            studentAnswers.add(studentAnswer);
        }
        return studentAnswers;
    }

    private Optional<Answer> askQuestionAndGetAnswer(String question, List<Answer> answers) {
        int answerIndex = askQuestionAndGetAnswerIndex(question, answers);
        return answers.stream()
                .filter(answer -> answer.getIndex() == answerIndex)
                .findAny();
    }

    private int askQuestionAndGetAnswerIndex(String question, List<Answer> answers) {
        ioService.outputQuestionAndAnswers(question, answers);
        return ioService.readInt();
    }

    private void resultChecking(User student, List<Optional<Answer>> answers) {
        int allQuestionsCount = answers.size();
        long correctAnswersCount = getCorrectAnswersCount(answers);
        outputResults(student, allQuestionsCount, correctAnswersCount);
    }

    private long getCorrectAnswersCount(List<Optional<Answer>> answers) {
        return answers.stream()
                .filter(answer -> answer.isPresent() && answer.get().getIsCorrect())
                .count();
    }

    private void outputResults(User student, int allQuestionsCount, long correctAnswersCount) {
        String[] messageParams = new String[]{student.getName(), student.getSurname(),
                String.valueOf(correctAnswersCount), String.valueOf(allQuestionsCount)};
        String resultMessage = messageSource.getMessage("test.result", messageParams, props.getLocale());
        ioService.outputString(resultMessage);
    }
}
