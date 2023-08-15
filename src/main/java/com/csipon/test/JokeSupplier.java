package com.csipon.test;

import java.util.List;

public interface JokeSupplier {
    
    List<Joke> getJokes(Integer count);

    Joke getJoke();
}
