package com.example.jars.context;

import com.example.testsubs.agregation.entity.Jar;

public class JarProducer {

    private Jar jar = null;

    public Jar aquireJar() {
        if(jar == null || jar.isFull())
            jar = new Jar();
        return jar;
    }
}
