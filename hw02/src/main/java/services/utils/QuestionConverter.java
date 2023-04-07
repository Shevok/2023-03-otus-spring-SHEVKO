package services.utils;

import model.Answer;
import model.Question;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class QuestionConverter {

    public QuestionConverter() {
    }

    public Map<Question, List<Answer>> transformInputStreamToQuestionsMap(InputStream is) {
        Map<Question, List<Answer>> questions = new HashMap<>();
        try (InputStreamReader streamReader = new InputStreamReader(is, StandardCharsets.UTF_8);
             BufferedReader reader = new BufferedReader(streamReader)) {

            String line;
            while ((line = reader.readLine()) != null) {
                String[] values = line.split(";");
                Question question = new Question(values[0]);
                List<Answer> answers = convertStringToAnswers(values[1]);
                questions.put(question, answers);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return questions;
    }

    private List<Answer> convertStringToAnswers(String answersString) {
        String[] answers = answersString.split(",");
        return Arrays.stream(answers)
                .map(Answer::new)
                .collect(Collectors.toList());
    }
}
