package com.example.exp0911.repository;

import com.example.exp0911.domain.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;


@DataJpaTest
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    void 사용자_생성_테스트() throws Exception {
        //given
        User user = User.createUser("test@gmail.com", "test1234");

        //when
        User saveUser = userRepository.save(user);

        //then
        User findUser = userRepository.findById(saveUser.getId()).orElse(null);

        Assertions.assertThat(findUser).isNotNull();
        Assertions.assertThat(findUser.getEmail()).isEqualTo("test@gmail.com");
    }

    @Test
    void 사용자_이메일_중복_테스트() throws Exception {
        //given
        User user = User.createUser("test@gmail.com", "test1234");
        User saveUser = userRepository.save(user);

        //when
        boolean isDuplicate = userRepository.existsByEmail(saveUser.getEmail());

        //then
        Assertions.assertThat(isDuplicate).isEqualTo(true);
    }
}