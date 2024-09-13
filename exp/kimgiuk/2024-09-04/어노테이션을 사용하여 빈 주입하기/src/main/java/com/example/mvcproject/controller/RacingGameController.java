package com.example.mvcproject.controller;

import com.example.mvcproject.domain.Car;
import com.example.mvcproject.domain.ElectricEngine;
import com.example.mvcproject.domain.V6Engine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class RacingGameController {
    private final Car car;
    private final V6Engine v6Engine;
    private final ElectricEngine electricEngine;

    @Autowired
    public RacingGameController(Car car, V6Engine v6Engine, ElectricEngine electricEngine) {
        this.car = car;
        this.v6Engine = v6Engine;
        this.electricEngine = electricEngine;
    }

    @GetMapping("/race")
    @ResponseBody
    public String race(@RequestParam(defaultValue = "v6") String engine) {
        car.setEngine(engine);
        car.startRace();
        return "race has started with " + engine + " engine!!!";
    }
}
