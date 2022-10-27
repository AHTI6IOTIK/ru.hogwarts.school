package ru.hogwarts.school.dto;

public class ServerPortResponseDto {
    private int serverPort;

    public ServerPortResponseDto(int serverPort) {
        this.serverPort = serverPort;
    }

    public int getServerPort() {
        return serverPort;
    }

    public ServerPortResponseDto setServerPort(int serverPort) {
        this.serverPort = serverPort;
        return this;
    }
}
