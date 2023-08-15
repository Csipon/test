package com.csipon.test;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class RestJokeSupplierTest {

    @Autowired
    private JokeSupplier jokeSupplier;

    @Test
    void shouldReturnJokeAsync() throws InterruptedException {

        int numThreads = 5;
        int numJokes = 3;

        ExecutorService executorService = Executors.newFixedThreadPool(numThreads);
        CountDownLatch latch = new CountDownLatch(numThreads);
        Queue<Joke> jokes = new ConcurrentLinkedQueue<>();
        for (int i = 0; i < numThreads; i++) {
            executorService.submit(() -> {
                for (int j = 0; j < numJokes; j++) {
                    Joke joke = jokeSupplier.getJoke();
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