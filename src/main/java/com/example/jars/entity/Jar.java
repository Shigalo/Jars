package com.example.jars.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Jar {

    @Getter
    List<Cucumber> cucumbers = new ArrayList<>();

    @Getter @Setter
    int maxVolume;

    public Jar() {
        maxVolume = new Random().nextInt(20,50);
    }

    public int getLeftVolume () {
        return maxVolume - cucumbers.stream().mapToInt(Cucumber::getVolume).sum();
    }

    public boolean isFull() {
        return maxVolume == cucumbers.stream().mapToInt(Cucumber::getVolume).sum();
    }

    public void addCucumber (Cucumber cucumber) {
        cucumbers.add(cucumber);
    }

    @Override
    public String toString() {
        return "Jar maxVolume = " + maxVolume + " :\n\t[cucumbers=" + cucumbers + "]";
    }


}
