package com.csipon.test;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Joke {
    private Integer id;
    private String type;
    private String setup;
    private String punchline;
}
