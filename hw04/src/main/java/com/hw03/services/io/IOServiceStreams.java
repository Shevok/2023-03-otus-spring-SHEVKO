package com.hw03.services.io;


import com.hw03.model.Answer;
import org.springframework.stereotype.Component;

import java.io.PrintStream;
import java.util.List;
import java.util.Scanner;

@Component
public class IOServiceStreams implements IOService {
    private final PrintStream output;

    private final Scanner input;

    public IOServiceStreams() {
        output = System.out;
        input = new Scanner(System.in);
    }

    @Override
    public void outputQuestionAndAnswers(String question, List<Answer> answers) {
        output.println(question);
        outputAnswers(answers);
    }

    @Override
    public void outputString(String outputString) {
        output.println(outputString);
    }

    @Override
    public int readInt() {
        return Integer.parseInt(input.nextLine());
    }

    @Override
    public String readString() {
        return String.valueOf(input.nextLine());
    }

    @Override
    public String outputStringAndReadString(String outputString){
        outputString(outputString);
        return readString();
    }

    private void outputAnswers(List<Answer> answers) {
        answers.forEach(answer -> output.print(answer.getValue() + " "));
    }
}
