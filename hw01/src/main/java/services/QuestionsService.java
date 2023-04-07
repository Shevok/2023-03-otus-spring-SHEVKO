package services;

import model.Answer;
import model.Question;

import java.util.List;
import java.util.Map;

public interface QuestionsService {

    Map<Question, List<Answer>> getQuestions();
}
