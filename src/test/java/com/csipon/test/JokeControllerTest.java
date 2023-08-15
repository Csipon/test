package com.csipon.test;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class JokeControllerTest {
    @Autowired
    MockMvc mockMvc;

    @MockBean
    private JokeSupplier jokeSupplier;


    @Test
    void shouldReturnOneJoke() throws Exception {
        when(jokeSupplier.getJokes(1)).thenReturn(joke());

        mockMvc.perform(get("/jokes?count=1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json("[\n" +
                        "    {\n" +
                        "        \"id\": 321,\n" +
                        "        \"type\": \"general\",\n" +
                        "        \"setup\": \"Why did the belt go to prison?\",\n" +
                        "        \"punchline\": \"He held up a pair of pants!\"\n" +
                        "    }" +
                        "]"));
    }

    @Test
    void shouldReturnMultipleJokes() throws Exception {
        when(jokeSupplier.getJokes(5)).thenReturn(jokes());

        mockMvc.perform(get("/jokes"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json("[\n" +
                        "    {\n" +
                        "        \"id\": 321,\n" +
                        "        \"type\": \"general\",\n" +
                        "        \"setup\": \"Why did the belt go to prison?\",\n" +
                        "        \"punchline\": \"He held up a pair of pants!\"\n" +
                        "    },\n" +
                        "    {\n" +
                        "        \"id\": 169,\n" +
                        "        \"type\": \"general\",\n" +
                        "        \"setup\": \"What did the big flower say to the littler flower?\",\n" +
                        "        \"punchline\": \"Hi, bud!\"\n" +
                        "    },\n" +
                        "    {\n" +
                        "        \"id\": 169,\n" +
                        "        \"type\": \"general\",\n" +
                        "        \"setup\": \"What did the big flower say to the littler flower?\",\n" +
                        "        \"punchline\": \"Hi, bud!\"\n" +
                        "    },\n" +
                        "    {\n" +
                        "        \"id\": 169,\n" +
                        "        \"type\": \"general\",\n" +
                        "        \"setup\": \"What did the big flower say to the littler flower?\",\n" +
                        "        \"punchline\": \"Hi, bud!\"\n" +
                        "    },\n" +
                        "    {\n" +
                        "        \"id\": 169,\n" +
                        "        \"type\": \"general\",\n" +
                        "        \"setup\": \"What did the big flower say to the littler flower?\",\n" +
                        "        \"punchline\": \"Hi, bud!\"\n" +
                        "    }\n" +
                        "]"));
    }

    @Test()
    void shouldReturnBadRequestAndOutOfLimitMessage() throws Exception {
        mockMvc.perform(get("/jokes?count=101"))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(content().string("You can get no more than 100 jokes at a time."));
    }

    private List<Joke> joke(){
        List<Joke> jokes = new ArrayList<>();
        jokes.add(new Joke(321, "general", "Why did the belt go to prison?", "He held up a pair of pants!" ));
        return jokes;
    }

    private List<Joke> jokes(){
        List<Joke> jokes = new ArrayList<>();
        jokes.add(new Joke(321, "general", "Why did the belt go to prison?", "He held up a pair of pants!" ));
        jokes.add(new Joke(169, "general", "What did the big flower say to the littler flower?", "Hi, bud!" ));
        jokes.add(new Joke(169, "general", "What did the big flower say to the littler flower?", "Hi, bud!" ));
        jokes.add(new Joke(169, "general", "What did the big flower say to the littler flower?", "Hi, bud!" ));
        jokes.add(new Joke(169, "general", "What did the big flower say to the littler flower?", "Hi, bud!" ));
        return jokes;
    }

}