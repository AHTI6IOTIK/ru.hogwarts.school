package ru.hogwarts.school.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.hogwarts.school.dto.ServerPortResponseDto;

@RestController
public class InfoController {
    @Value("${server.port}")
    private int serverPort;

    @GetMapping("/getPort")
    public ServerPortResponseDto getPort() {
        return (new ServerPortResponseDto(serverPort));
    }
}
