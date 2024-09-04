package com.spring.exp.mission0904;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class RacingGameController {

    private final Car car;

    @Autowired
    public RacingGameController(Car car) {
        this.car = car;
    }

    // http://localhost:8080/race
    @GetMapping("/race")
    @ResponseBody
    public String race() {
        car.startRace();
        return "race start";
    }
}
