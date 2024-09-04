package com.spring.controller;

import com.spring.model.Car;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class RaceController {
    private final Car car;

    public RaceController(Car car) {
        this.car = car;
    }

    @RequestMapping("/race")
    @ResponseBody
    public String race() {
        car.startRace();
        return "race start";
    }
}
