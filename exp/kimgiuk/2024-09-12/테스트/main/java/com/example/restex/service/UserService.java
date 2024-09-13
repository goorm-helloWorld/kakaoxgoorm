package com.example.restex.service;

import com.example.restex.dto.UserDto;
import com.example.restex.entity.User;
import com.example.restex.repository.UserRepository;
import jdk.jfr.StackTrace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // 사용자 생성 : 사용자 등록
    @Transactional
    public User createUser(UserDto userDto){
        User user = new User();
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        return userRepository.save(user);
    }

    @Transactional(rollbackFor = Exception.class)
    public User createUserWithRollback(UserDto userDto) throws Exception{
        User user = new User();
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        userRepository.save(user);

        // 강제로 예외 발생
        if (userDto.getEmail().contains("error")){
            throw new Exception("강제 예외 발생");
        }

        return user;
    }

    // 사용자 정보 조회
    @Transactional(readOnly = true)
    public User getUserById(Long id){
        return userRepository.findById(id).orElse(null);
    }

    // 사용자 정보 업데이트
    // 트랜잭션 전파수준, 격리수준 설정
    @Transactional(propagation = Propagation.REQUIRES_NEW, isolation = Isolation.REPEATABLE_READ)
    public User updateUser(Long id, UserDto userDto){
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        return userRepository.save(user);
    }
}
