package com.csipon.test;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@RequiredArgsConstructor
@Component
public class RestJokeSupplier implements JokeSupplier{

    private final RestTemplate restTemplate;
    @Value("${joke.resource.url}")
    private String jokeResourceUrl;

    @Override
    public List<Joke> getJokes(Integer count) {
        List<Joke> jokes = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            Joke joke = getJoke();
            jokes.add(joke);
        }
        return jokes;
    }

    @Async("threadPoolTaskExecutor")
    public Joke getJoke(){
        return restTemplate.getForObject(jokeResourceUrl, Joke.class);
    }
}
