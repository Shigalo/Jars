package com.example.jars.services;

import reactor.core.publisher.Flux;

public interface Processor<T, V> {

    Flux<V> doProcess(Flux<T> flux);
}
