package com.hw03.services.io;


import com.hw03.model.Answer;

import java.util.List;

public interface OutputService {
    void outputQuestionAndAnswers(String question, List<Answer> answers);

    void outputString(String outputString);
}
