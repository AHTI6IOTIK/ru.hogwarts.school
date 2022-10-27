package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;

import java.util.stream.Stream;

@Service
public class InfoService {
    public int withoutParallelCheck() {
        return getStream()
            .reduce(Integer::sum)
            .orElse(0)
        ;
    }

    public int withParallelCheck() {
        return getStream()
            .parallel()
            .reduce(Integer::sum)
            .orElse(0)
        ;
    }

    private Stream<Integer> getStream() {
        return Stream.iterate(1, a -> a + 1)
            .limit(100_000_000);
    }
}
