package com.example.jars.services;

import reactor.core.publisher.Flux;

public interface ProcessService<T, V> {

    Flux<V> process(Flux<T> flux);
}
