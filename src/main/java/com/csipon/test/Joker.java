package com.csipon.test;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
@RequiredArgsConstructor
public class Joker {

    private final JokeSupplier jokeSupplier;

    public List<Joke> sayJokes(Integer count) {
        if (count > 100) {
            throw new JokeLimitException("You can get no more than 100 jokes at a time.");
        }

        return jokeSupplier.getJokes(count);
    }
}

