package com.csipon.test;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Queue;
import java.util.concurrent.*;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class RestJokeSupplierTest {

    @Autowired
    private JokeSupplier jokeSupplier;

    @SneakyThrows
    @Test
    void shouldReturnJokeAsync() {

        int numThreads = 5;
        int numJokes = 10;

        ExecutorService executorService = Executors.newFixedThreadPool(numThreads);
        CountDownLatch latch = new CountDownLatch(numThreads);
        Queue<Joke> jokes = new ConcurrentLinkedQueue<>();
        for (int i = 0; i < numThreads; i++) {
            executorService.submit(() -> {
                for (int j = 0; j < numJokes; j++) {
                    Joke joke;
                    try {
                        joke = jokeSupplier.getJoke().get();
                    } catch (InterruptedException | ExecutionException e) {
                        throw new RuntimeException(e);
                    }
                    jokes.add(joke);
                }
                latch.countDown();
            });
        }

        latch.await();

        assertEquals(numThreads * numJokes, jokes.size());
        executorService.shutdown();
    }
}