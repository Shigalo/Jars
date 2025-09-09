package com.example.jars.services;

import com.example.jars.dataSources.JarDataService;
import com.example.jars.entity.Cucumber;
import com.example.jars.entity.Jar;
import reactor.core.publisher.Flux;

public class AggregationService implements ProcessService<Cucumber, Jar> {


    @Override
    public Flux<Jar> process(Flux<Cucumber> cucumberFlux) {

        Flux<Jar> resultFlux = cucumberFlux
                .<Jar>handle((cucumber, sink) -> {

                    JarDataService jarDataService = sink.currentContext().get(JarDataService.class);
                    Cucumber currentCucumber = cucumber;

                    do {
                        Jar currentJar = jarDataService.aquire();

                        if (currentJar.volumeIsEnough(currentCucumber)) {
                            currentJar.addCucumber(currentCucumber);

                            currentCucumber = null;
                        } else {
                            Cucumber newCucumber = cutAndGetNew(currentCucumber, currentJar.getEmptyVolume());
                            currentJar.addCucumber(currentCucumber);
                            currentCucumber = newCucumber;
                        }

                        if (currentJar.isFull()) {
                            sink.next(currentJar);
                        }
                    } while (cucumberProcessedValidation(currentCucumber));
        }).contextWrite(ctx -> ctx.put(JarDataService.class, new JarDataService()));

        return resultFlux;
    }

    boolean cucumberProcessedValidation(Cucumber cucumber) {
        return cucumber != null;
    }

    Cucumber cutAndGetNew(Cucumber currentCucumber, int volumeToCut) {
        int leftovers = currentCucumber.getVolume() - volumeToCut;
        currentCucumber.setVolume(volumeToCut);
        return new Cucumber(leftovers);
    }
}
