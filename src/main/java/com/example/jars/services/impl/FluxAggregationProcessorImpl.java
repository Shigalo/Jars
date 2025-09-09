package com.example.jars.services.impl;

import com.example.jars.data.entity.Cucumber;
import com.example.jars.data.entity.Jar;
import com.example.jars.services.Processor;
import reactor.core.publisher.Flux;

public class FluxAggregationProcessorImpl implements Processor<Cucumber, Jar> {

    @Override
    public Flux<Jar> doProcess(Flux<Cucumber> cucumberFlux) {
        return cucumberFlux.log().handle(new AggregationProcessorImpl());
    }
}
