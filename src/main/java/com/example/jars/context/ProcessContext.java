package com.example.jars.context;

import com.example.jars.entity.Cucumber;
import com.example.jars.entity.Jar;
import com.example.jars.dataSources.CucumberDataService;
import com.example.jars.dataSources.ProducerDataService;
import com.example.jars.services.AggregationService;
import com.example.jars.services.ProcessService;
import reactor.core.publisher.Flux;

public class ProcessContext {

    public static void main(String[] args) {

        ProducerDataService<Cucumber> cucumberDataService = new CucumberDataService();
        ProcessService<Cucumber, Jar> aggregationService = new AggregationService();


        Flux<Cucumber> cucumberFlux = cucumberDataService.produceFlux();
        Flux<Jar> resulFlux = aggregationService.process(cucumberFlux);

        resulFlux.subscribe(System.out::println);
        cucumberDataService.requestData();
    }


}
