package com.spring.exp.mission0904;

import org.springframework.stereotype.Component;

@Component
public class ElectricEngine implements Engine {

    @Override
    public String start() {
        return "Electric Engine is starting…";
    }
}
