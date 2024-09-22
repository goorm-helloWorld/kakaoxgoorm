package com.example.restapi_ex.controller;

import com.example.restapi_ex.dto.UserDto;
import com.example.restapi_ex.entity.User;
import com.example.restapi_ex.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // 사용자 등록
    @PostMapping
    public User createUser(@RequestBody UserDto userDto) throws Exception {
        return userService.createUser(userDto);
        //b=return userService.createUserWithRollBack(userDto);
    }

    // 사용자 조회
    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    // 사용자 업데이트
    @PutMapping("/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody UserDto userDto) {
        return userService.updateUser(id, userDto);
    }
}
