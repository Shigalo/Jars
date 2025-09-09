package com.example.jars.dataSources;

import com.example.jars.entity.Cucumber;
import lombok.Getter;
import lombok.Setter;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

import java.util.Random;

import static reactor.core.publisher.Sinks.EmitFailureHandler.FAIL_FAST;

public class CucumberDataService implements ProducerDataService<Cucumber> {

    private final Sinks.Many<Cucumber> source = Sinks.many().multicast().directBestEffort();

    @Getter @Setter
    Cucumber cucumber;

    @Override
    public Flux<Cucumber> produceFlux() {
        return source.asFlux();
    }

    @Override
    public Cucumber aquire() {
        return cucumber;
    }

    public void requestData() {
        for (int i = 1; i < 101; i++) {
            int volume = new Random().nextInt(2, 20);
            Cucumber cucumber = new Cucumber(volume);
            addNewCucumber(cucumber);
        }
    }

    public void addNewCucumber(Cucumber cucumber) {
        System.out.println(cucumber);
        source.emitNext(cucumber, FAIL_FAST);
    }
}
