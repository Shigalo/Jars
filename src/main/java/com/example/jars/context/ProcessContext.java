package com.example.jars.context;

import com.example.testsubs.agregation.entity.Cucumber;
import com.example.testsubs.agregation.entity.Jar;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

import java.util.Random;

import static reactor.core.publisher.Sinks.EmitFailureHandler.FAIL_FAST;

public class ProcessContext {

    public static void main(String[] args) {

        Sinks.Many<Cucumber> cucumberSource = Sinks.many().multicast().directBestEffort();
        Flux<Cucumber> cucumberFlux = cucumberSource.asFlux();

        Flux<Jar> resulFlux = cucumberFlux
                .handle(Service::putInside)
                .contextWrite(ctx -> ctx.put(JarProducer.class, new JarProducer()));

        //output (J output)
        resulFlux.subscribe(System.out::println);

        //Start (fill C flux)
        for (int i = 1; i < 101; i++) {
            int volume = new Random().nextInt(2, 20);
            Cucumber cucumber = new Cucumber(volume);
            System.out.println(cucumber);
            cucumberSource.emitNext(cucumber, FAIL_FAST);
        }
    }
}
