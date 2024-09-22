package com.example.restapi_ex.service;

import com.example.restapi_ex.dto.UserDto;
import com.example.restapi_ex.entity.User;
import com.example.restapi_ex.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public User createUser(UserDto userDto) {
        User user = new User();
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        return userRepository.save(user);
    }

    @Transactional(rollbackFor = Exception.class)
    public User createUserWithRollBack(UserDto userDto) throws Exception {
        User user = new User();
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        userRepository.save(user);

        // 강제로 예외 발생
        if (userDto.getEmail().contains("error")) {
            throw new Exception("강제 예외 발생");
        }

        return user;

    }

    @Transactional(readOnly = true)
    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    // 트랜잭션 전파수준, 격리수준 설정
    @Transactional(propagation = Propagation.REQUIRES_NEW, isolation = Isolation.REPEATABLE_READ)
    public User updateUser(Long id, UserDto userDto) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        return userRepository.save(user);
    }
}
