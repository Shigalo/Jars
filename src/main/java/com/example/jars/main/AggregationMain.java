package com.example.jars.main;

import com.example.jars.data.entity.Cucumber;
import com.example.jars.data.entity.Jar;
import com.example.jars.data.producers.impl.CucumberDataProducer;
import com.example.jars.services.impl.FluxAggregationProcessorImpl;
import com.example.jars.services.Processor;
import reactor.core.publisher.Flux;

public class AggregationMain {

    public static void main(String[] args) {

        CucumberDataProducer cucumberDataProducer = new CucumberDataProducer();
        Processor<Cucumber, Jar> aggregationProcessor = new FluxAggregationProcessorImpl();

        int count = 20;
        int minVolume = 2;
        int maxVolume = 20;

        Flux<Cucumber> cucumberFlux = cucumberDataProducer.getFlux(count, minVolume, maxVolume);
        Flux<Jar> resulFlux = aggregationProcessor.doProcess(cucumberFlux);
        resulFlux.subscribe(System.out::println);
    }
}
