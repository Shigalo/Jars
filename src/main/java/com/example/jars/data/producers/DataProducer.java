package com.example.jars.data.producers;

import reactor.core.publisher.Flux;

public interface DataProducer<T> {

    Flux<T> getFlux(int count, int minVolume, int maxVolume);
}
