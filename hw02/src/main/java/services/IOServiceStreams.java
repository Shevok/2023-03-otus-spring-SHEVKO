package services;

import model.Answer;
import model.Question;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class IOServiceStreams implements IOService {
    private final PrintStream output;

    private final Scanner input;

    public IOServiceStreams(PrintStream outputStream, InputStream inputStream) {
        output = outputStream;
        input = new Scanner(inputStream);
    }

    @Override
    public void outputQuestions(Map<Question, List<Answer>> questions) {
        for (var entry : questions.entrySet()) {
            output.println(entry.getKey().getValue());
            outputAnswers(entry.getValue());
            readInt();
        }
    }

    @Override
    public int readInt() {
        return Integer.parseInt(input.nextLine());
    }

    private void outputAnswers(List<Answer> answers) {
        answers.forEach(answer -> output.print(answer.getValue() + " "));
    }
}
