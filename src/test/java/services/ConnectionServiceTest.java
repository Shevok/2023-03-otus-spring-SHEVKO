package services;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ConnectionServiceTest {

    @Test
    void getFileName() {
        ConnectionService connectionService = new ConnectionService("name");
        assertEquals("name", connectionService.getFileName());
    }
}