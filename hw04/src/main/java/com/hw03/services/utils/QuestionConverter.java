package com.hw03.services.utils;


import com.hw03.model.Answer;
import com.hw03.model.Question;
import org.springframework.stereotype.Service;

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

@Service
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

    private List<Answer> convertStringToAnswers(String answersAndCorrectIndexString) {
        String[] answersAndCorrectIndex = answersAndCorrectIndexString.split(":");
        String[] answers = answersAndCorrectIndex[0].split(",");
        Integer correctAnswerIndex = Integer.parseInt(answersAndCorrectIndex[1]);
        return Arrays.stream(answers)
                .map((answersString) -> createAnswer(answersString,correctAnswerIndex))
                .collect(Collectors.toList());
    }

    private Answer createAnswer(String answersString, Integer correctAnswerIndex) {
        String[] indexAndAnswer = answersString.split("\\)");
        int answerIndex = Integer.parseInt(indexAndAnswer[0]);
        Boolean isCorrect = answerIndex == correctAnswerIndex;
        return new Answer(answerIndex, answersString, isCorrect);
    }
}
