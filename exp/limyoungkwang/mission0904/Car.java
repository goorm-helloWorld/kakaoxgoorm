package com.spring.exp.mission0904;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class Car {

    private Engine engine;

    @Autowired
    public Car(@Qualifier("v6Engine")Engine engine) {
        this.engine = engine;
    }

    public void startRace(){
        System.out.println(engine.start());;
    }
}
