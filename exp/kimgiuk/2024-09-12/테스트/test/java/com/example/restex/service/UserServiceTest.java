package com.example.restex.service;

import com.example.restex.dto.UserDto;
import com.example.restex.entity.User;
import com.example.restex.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    // 사용자 생성 : 사용자 등록 테스트
    @Test
    void testCreateUser(){
        // 1. 테스트용 UserDto 준비
        UserDto userDto = new UserDto();
        userDto.setName("John");
        userDto.setEmail("john@gmail.com");

        // 2. Mockito를 사용. 리포지토리의 save 메서드를 모킹
        User user = new User();
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        when(userRepository.save(any(User.class))).thenReturn(user);

        // 3. 서비스 메서드 호출 및 결과 검증
        User createdUser = userService.createUser(userDto);
        assertEquals(userDto.getName(), createdUser.getName());
        assertEquals(userDto.getEmail(), createdUser.getEmail());

        // 4. 리포지토리의 save 메서드가 호출되었는지 확인
        verify(userRepository, times(1)).save(any(User.class));
    }

    // 예외 처리 테스트
    @Test
    void testCreateUserWithRollback() throws Exception{
        // 테스트용 UserDto 준비
        UserDto userDto = new UserDto();
        userDto.setName("Jane");
        userDto.setEmail("error@test.com");

        // 모킹된 UserRepository가 동작하도록 설정
        when(userRepository.save(any(User.class))).thenAnswer(i->i.getArguments()[0]);

        // 예외 발생을 위한 설정
        assertThrows(Exception.class, ()->userService.createUserWithRollback(userDto));

        // userRepository.save() 호출 검증
        verify(userRepository, times(1)).save(any(User.class));
    }
}
