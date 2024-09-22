package com.example.restapi_ex.controller;

import com.example.restapi_ex.dto.ItemDto;
import com.example.restapi_ex.dto.ResponseDto;
import com.example.restapi_ex.dto.UserDto;
import com.example.restapi_ex.service.RestExService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
public class RestExController {

    private final RestExService restExService;

    public RestExController(RestExService restExService) {
        this.restExService = restExService;
    }

    // http://localhost:8080/test
    @GetMapping("/test")
    public String test() {
        log.info("test");
        return "{}";
    }

    @GetMapping("/test2")
    public String test2() {
        log.info("test2");
        return "test2";
    }

    // http://localhost:8080/param?name=Spring
    @GetMapping("/param")
    public String testRequestParam(@RequestParam String name) {
        log.info("RequestParam : " + name);
        return name;
    }

    // http://localhost:8080/param/Spring
    @GetMapping("/path/{name}")
    public String testPathVariable(@PathVariable String name) {
        log.info("PathVariable : " + name);
        return name;
    }

    // @RequestBody를 사용, 데이터를 객체로 바인딩
    @PostMapping("/body")
    public String testRequestBody(@RequestBody UserDto user) {
        log.info("RequestBody : " + user);
        return "RequestBody : " + user;
    }

    @GetMapping("/item")
    public ItemDto getItem(@RequestParam("id") String id) {
        ItemDto res = restExService.getItem(id);
        return res;
    }

    @PostMapping("/item")
    public ResponseDto testRequestBody(@RequestBody ItemDto item) {
        log.info("RequestBody : " + item);

        boolean b = restExService.registerItem(item);
        if (b) {
            ResponseDto responseDto = new ResponseDto();
            responseDto.setMessage("ok");
            return responseDto;
        }
        ResponseDto responseDto = new ResponseDto();
        responseDto.setMessage("fail");
        return responseDto;
    }
}
