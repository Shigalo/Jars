package com.example.jars.dataSources;

import reactor.core.publisher.Flux;

public interface ProducerDataService<T> {

    Flux<T> produceFlux();

    T aquire();

    void requestData();
}
