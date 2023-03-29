import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import services.QuestionsService;

public class App {
    public static void main(String[] argv) {
        ApplicationContext appContext = new ClassPathXmlApplicationContext("context/spring-context.xml");
        QuestionsService questionsService = appContext.getBean(QuestionsService.class);
        questionsService.startAskQuestions();
    }
}
