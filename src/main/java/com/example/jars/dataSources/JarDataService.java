package com.example.jars.dataSources;


import com.example.jars.entity.Jar;
import reactor.core.publisher.Flux;

public class JarDataService implements ProducerDataService<Jar> {

    private Jar jar = null;

    @Override
    public Jar aquire() {
        if(jar == null || jar.isFull())
            jar = new Jar();
        return jar;
    }

    @Override
    public Flux<Jar> produceFlux() {
        return null;
    }

    @Override
    public void requestData() {

    }
}
