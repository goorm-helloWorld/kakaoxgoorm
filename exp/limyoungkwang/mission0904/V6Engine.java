package com.spring.exp.mission0904;

import org.springframework.stereotype.Component;

@Component
public class V6Engine implements Engine {

    @Override
    public String start() {
        return "V6 Engine is starting…";
    }
}
