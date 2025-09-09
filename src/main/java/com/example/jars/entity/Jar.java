package com.example.jars.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Jar {

    @Getter
    List<Cucumber> cucumbersList = new ArrayList<>();

    @Getter @Setter
    int maxVolume;

    public Jar() {
        maxVolume = new Random().nextInt(20,50);
    }

    public boolean volumeIsEnough(Cucumber cucumber) {
        return getEmptyVolume() - cucumber.volume >= 0;
    }

    public int getEmptyVolume() {
        return maxVolume - cucumbersList.stream().mapToInt(Cucumber::getVolume).sum();
    }

    public boolean isFull() {
        return maxVolume == cucumbersList.stream().mapToInt(Cucumber::getVolume).sum();
    }

    public void addCucumber(Cucumber cucumber) {
        cucumbersList.add(cucumber);
    }

    @Override
    public String toString() {
        return "Jar maxVolume = " + maxVolume + " :\n\t[cucumbers=" + cucumbersList + "]";
    }


}
