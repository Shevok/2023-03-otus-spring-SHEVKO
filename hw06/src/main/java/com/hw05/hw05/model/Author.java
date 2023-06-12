package com.hw05.hw05.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Author {

    private long id;

    private String name;

    public Author(String name) {
        this.name = name;
    }
}
