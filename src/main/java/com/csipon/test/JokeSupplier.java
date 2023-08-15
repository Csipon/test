package com.csipon.test;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface JokeSupplier {

    List<Joke> getJokes(Integer count);

    CompletableFuture<Joke> getJoke();
}
