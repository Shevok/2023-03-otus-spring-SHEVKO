import model.Answer;
import model.Question;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import services.IOServiceStreams;
import services.QuestionsService;

import java.util.List;
import java.util.Map;

public class App {
    public static void main(String[] argv) {
        ApplicationContext appContext = new ClassPathXmlApplicationContext("context/spring-context.xml");
        QuestionsService questionsService = appContext.getBean(QuestionsService.class);
        var ioService = new IOServiceStreams(System.out, System.in);
        Map<Question, List<Answer>> questions = questionsService.getQuestions();
        ioService.outputQuestions(questions);
    }
}
