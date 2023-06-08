package services;

import model.Answer;
import model.Question;

import java.util.List;
import java.util.Map;

public interface OutputService {
    void outputQuestions(Map<Question, List<Answer>> questions);
}
