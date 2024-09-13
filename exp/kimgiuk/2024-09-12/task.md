# UserControllerTest 사용자 조회 API 테스트

데이터베이스에 미리 저장한 사용자를 조회하는 API를 호출하고 올바르게 조회되었는지 검증하는 통합 테스트를 진행합니다.

## 진행 순서
1. **사용자 저장 (테스트 준비)**: 테스트를 위한 새로운 사용자를 저장합니다.
2. **사용자 조회 API 호출**: `restTemplate.getForEntity("/user/" + user.getId(), User.class)`를 사용해, path variable을 전달합니다.
3. **응답 상태 및 내용 검증**: 응답 상태 코드 및 내용을 검증합니다.

## UserControllerTest 클래스의 testGetUserById() 메서드 코드
- 아래는 `UserControllerTest` 클래스의 `testGetUserById()` 메서드 코드 스크린샷입니다.
```java
@Test
public void testGetUserById() {
    // 1. 사용자 저장 (테스트 준비)
    User user = new User();
    user.setName("테스트 사용자");
    user.setEmail("test@example.com");
    userRepository.save(user);

    // 2. 사용자 조회 API 호출
    ResponseEntity<User> response = restTemplate.getForEntity("/user/" + user.getId(), User.class);

    // 3. 응답 상태 및 내용 검증
    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertNotNull(response.getBody());
    assertEquals("테스트 사용자", response.getBody().getName());
}