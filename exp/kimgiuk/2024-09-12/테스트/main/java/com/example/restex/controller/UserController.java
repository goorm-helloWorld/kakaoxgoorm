package com.example.restex.controller;

import com.example.restex.dto.UserDto;
import com.example.restex.entity.User;
import com.example.restex.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    // 사용자 생성/등록 API
    @PostMapping
    public User createUser(@RequestBody UserDto userDto){
        return userService.createUser(userDto);
    }
//    public User createUser(@RequestBody UserDto userDto) throws Exception {
//        return userService.createUserWithRollback(userDto);
//    }

    // 사용자 조회 API
    @GetMapping("/{id}")
    public User getUserById(@PathVariable long id) {
        return userService.getUserById(id);
    }

    // 사용자 정보 업데이트 API
    @PutMapping("/{id}")
    public User updateUser(@PathVariable long id, @RequestBody UserDto userDto) {
        return userService.updateUser(id, userDto);
    }
}
