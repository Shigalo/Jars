package com.example.jars.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
public class Cucumber {

    @Getter @Setter
    int volume;

    @Override
    public String toString() {
        return "C[" + volume + "]";
    }
}
