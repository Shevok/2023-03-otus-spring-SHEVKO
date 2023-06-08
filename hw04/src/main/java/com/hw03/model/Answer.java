package com.hw03.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Answer {

    private Integer index;

    private String value;

    private Boolean isCorrect;

}
