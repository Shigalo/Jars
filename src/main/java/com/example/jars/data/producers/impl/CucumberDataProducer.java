package com.example.jars.data.producers.impl;

import com.example.jars.data.entity.Cucumber;
import com.example.jars.data.producers.DataProducer;
import reactor.core.publisher.Flux;

import java.util.Random;

public class CucumberDataProducer implements DataProducer<Cucumber> {

    @Override
    public Flux<Cucumber> getFlux(int count, int minVolume, int maxVolume) {

        return Flux.generate(() -> 1, (state, sink) -> {
            if (state <= count) {
                sink.next(new Cucumber(new Random().nextInt(minVolume, maxVolume)));
            } else {
                sink.complete();
            }
            return state + 1;
        });
    }
}
