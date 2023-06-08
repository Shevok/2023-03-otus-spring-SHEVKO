package com.hw03.services.question;


import com.hw03.model.Answer;
import com.hw03.model.Question;

import java.util.List;
import java.util.Map;

public interface QuestionsService {

    Map<Question, List<Answer>> getQuestionsAndAnswers();
}
