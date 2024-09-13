package com.example.restex.controller;

import com.example.restex.dto.UserDto;
import com.example.restex.entity.User;
import com.example.restex.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserControllerTest {

    @Autowired
    private TestRestTemplate restTemplate; // REST 요청을 테스트하기 위한 TestRestTemplate

    @Autowired
    private UserRepository userRepository; // 실제 데이터베이스와 상호작용하는 JPA 리포지토리

    @BeforeEach
    void setUp() {
        System.out.println("[테스트 준비] 데이터베이스의 모든 사용자 데이터 삭제");
        userRepository.deleteAll(); // 테스트 전 데이터 초기화
    }

    // 사용자 생성(등록) API 테스트
    @Test
    void testCreateUser() {
        // 1. 테스할 사용자 DTO 생성
        System.out.println("[테스트] 새로운 사용자 John 생성 준비");
        UserDto userDto = new UserDto();
        userDto.setName("John");
        userDto.setEmail("john@gmail.com");

        // 2. HTTP 요청을 위한 HttpEntity 생성
        System.out.println("[테스트] HttpEntity 생성하여 사용자 데이터 준비");
        HttpEntity<UserDto> request = new HttpEntity<>(userDto);

        // 3. REST API 호출 - POST 요청
        System.out.println("[테스트] /user 경로로 POST 요청을 보내기");
        ResponseEntity<User> response = restTemplate.exchange("/user", HttpMethod.POST, request, User.class);

        // 4. 응답 상태 및 내용 검증
        System.out.println("[검증] 응답 상태코드 : " + response.getStatusCode());
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK); // 응답 상태코드 확인
        assertThat(response.getBody().getName()).isEqualTo("John"); // 응답 내용 검증
        assertThat(response.getBody().getEmail()).isEqualTo("john@gmail.com");

        System.out.println("[성공] 사용자 생성 테스트 성공");
    }
    
    // exp 미션
    // 사용자 조회 API 테스트
    // : 데이터베이스에 미리 저장한 사용자를 조회하는 API를 호출
    //   올바르게 조회되었는지 검증
    @Test
    void testGetUserById() {
        User user = new User();
        user.setName("Jane");
        user.setEmail("jane@gmail.com");

        User savedUser = userRepository.save(user);

        ResponseEntity<User> response = restTemplate.getForEntity("/user/" + savedUser.getId(), User.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody().getName()).isEqualTo("Jane");
        assertThat(response.getBody().getEmail()).isEqualTo("jane@gmail.com");
    }
}
