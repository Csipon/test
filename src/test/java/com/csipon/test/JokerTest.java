package com.csipon.test;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
class JokerTest {
    @Mock
    JokeSupplier supplier;
    @InjectMocks
    Joker joker;

    @Test
    void shouldReturnJokes() {
        doReturn(jokes()).when(supplier).getJokes(anyInt());

        List<Joke> actual = joker.sayJokes(2);

        assertArrayEquals(jokes().toArray(), actual.toArray());
    }

    @Test
    @SneakyThrows
    void expectedLimitExceptionWhenJokesCountMoreThan_100() {
        assertThrows(JokeLimitException.class, () -> joker.sayJokes(101));
    }

    private List<Joke> jokes() {
        List<Joke> jokes = new ArrayList<>();
        jokes.add(new Joke(321, "general", "Why did the belt go to prison?", "He held up a pair of pants!"));
        jokes.add(new Joke(169, "general", "What did the big flower say to the littler flower?", "Hi, bud!"));
        return jokes;
    }
}