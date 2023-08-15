package com.csipon.test;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class JokeController {

    private final Joker joker;

    @GetMapping("/jokes")
    public List<Joke> tellJoke(@RequestParam(defaultValue = "5") Integer count) {
        return joker.sayJokes(count);
    }
}
