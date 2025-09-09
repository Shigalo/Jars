package com.example.jars.context;

import com.example.testsubs.agregation.entity.Cucumber;
import com.example.testsubs.agregation.entity.Jar;
import reactor.core.publisher.SynchronousSink;

public class Service {

    public static void putInside(Cucumber cucumber, SynchronousSink<Jar> sink) {

        Jar jar = sink.currentContext().get(JarProducer.class).aquireJar();
        int leftVolume = jar.getLeftVolume();

        //check volume
        if(cucumber.getVolume() <= leftVolume) {
            //enough
            jar.addCucumber(cucumber);      //add C

            //full
            if(jar.isFull()){
                sink.next(jar);             //sink J
            }
        }
        //not enough
        else {
            int cutVolume = cucumber.getVolume() - leftVolume;

            cucumber.setVolume(leftVolume); //cut
            jar.addCucumber(cucumber);      //add old C

            sink.next(jar);                 //sink J

            putInside(new Cucumber(cutVolume), sink);

        }
    }
}
