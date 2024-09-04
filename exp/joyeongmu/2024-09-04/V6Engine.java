package com.spring.model;

import org.springframework.stereotype.Component;


@Component
public class V6Engine implements Engine{
    @Override
    public void start() {
        System.out.println("V6 Engine is starting ...");
    }
}
