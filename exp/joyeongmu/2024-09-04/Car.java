package com.spring.model;


import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class Car {
    private final Engine engine;

    public Car(@Qualifier("v6Engine") Engine engine) {
        this.engine = engine;
    }

    public void startRace() {
        engine.start();
    }
}
