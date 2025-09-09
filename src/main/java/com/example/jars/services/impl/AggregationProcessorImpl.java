package com.example.jars.services.impl;

import com.example.jars.data.entity.Cucumber;
import com.example.jars.data.entity.Jar;
import com.example.jars.services.BiConsumerInterface;
import reactor.core.publisher.SynchronousSink;

public class AggregationProcessorImpl implements BiConsumerInterface<Cucumber, SynchronousSink<Jar>> {

    private final int MIN_JAR_VOLUME = 20;
    private final int MAX_JAR_VOLUME = 50;

    Jar currentJar = new Jar(MIN_JAR_VOLUME, MAX_JAR_VOLUME);
    
    @Override
    public void accept(Cucumber cucumber, SynchronousSink<Jar> sink) {
        Cucumber cucumberInProcess = cucumber;
        do {
            if (volumeIsEnough(currentJar, cucumberInProcess)) {
                currentJar.addCucumber(cucumberInProcess);
                cucumberInProcess = null;
            } else {
                Cucumber newCucumber = cutAndGetNew(cucumberInProcess, currentJar.getEmptyVolume());
                currentJar.addCucumber(cucumberInProcess);
                cucumberInProcess = newCucumber;
            }

            if (currentJar.isFull()) {
                sink.next(currentJar);
                getNewJar();
            }
        } while (cucumberProcessedValidation(cucumberInProcess));
    }

    public boolean volumeIsEnough(Jar jar, Cucumber cucumber) {
        return jar.getEmptyVolume() >= cucumber.getVolume();
    }

    void getNewJar() {
        currentJar = new Jar(MIN_JAR_VOLUME, MAX_JAR_VOLUME);
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
