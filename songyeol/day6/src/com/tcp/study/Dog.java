package com.tcp.study;

/**
 * Created by Sonkrat on 2016. 11. 20..
 */
public class Dog {
    private String breed;

    public Dog(String breed) {
        this.breed = breed;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    @Override
    public String toString() {
        return "Dog [breed=" + breed + "]";
    }
}
