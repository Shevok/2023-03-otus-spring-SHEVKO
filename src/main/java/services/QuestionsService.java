package services;

import java.io.InputStream;

public class QuestionsService {

    private ConnectionService connectionService;

    private ResourceService resourceService;

    public QuestionsService(ConnectionService connectionService, ResourceService resourceService) {
        this.connectionService = connectionService;
        this.resourceService = resourceService;
    }

    public void startAskQuestions(){
        String fileName = connectionService.getFileName();
        InputStream is = resourceService.getFileFromResourceAsStream(fileName);
        resourceService.printInputStream(is);
    }
}
