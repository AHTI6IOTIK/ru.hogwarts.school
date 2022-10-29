package ru.hogwarts.school.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.hogwarts.school.dto.ParallelDto;
import ru.hogwarts.school.dto.ServerPortResponseDto;
import ru.hogwarts.school.service.InfoService;

@RestController
public class InfoController {
    @Value("${server.port}")
    private int serverPort;

    private final InfoService service;

    public InfoController(InfoService service) {
        this.service = service;
    }

    @GetMapping("/getPort")
    public ServerPortResponseDto getPort() {
        return (new ServerPortResponseDto(serverPort));
    }

    @GetMapping("/parallel/without")
    public ResponseEntity<ParallelDto> withoutParallelCheck() {
        ParallelDto parallelDto = new ParallelDto();
        long startTime = System.currentTimeMillis();
        parallelDto.computedValue = service.withoutParallelCheck();
        long endTime = System.currentTimeMillis();
        parallelDto.executionTime = endTime - startTime;
        return ResponseEntity.ok(parallelDto);
    }

    @GetMapping("/parallel/with")
    public ResponseEntity<ParallelDto> withParallelCheck() {
        ParallelDto parallelDto = new ParallelDto();
        long startTime = System.currentTimeMillis();
        parallelDto.computedValue = service.withParallelCheck();
        long endTime = System.currentTimeMillis();
        parallelDto.executionTime = endTime - startTime;
        return ResponseEntity.ok(parallelDto);
    }
}
