package services;

public class ConnectionService {
    private String fileName;

    public ConnectionService(String fileName) {
        this.fileName = fileName;
    }

    public String getFileName() {
        return fileName;
    }
}
