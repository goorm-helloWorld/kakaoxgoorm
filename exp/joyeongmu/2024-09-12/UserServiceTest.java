package com.example.exp0911.service;

import com.example.exp0911.controller.dto.IdDto;
import com.example.exp0911.controller.dto.request.UserCreateReqDto;
import com.example.exp0911.controller.dto.response.UserResDto;
import com.example.exp0911.domain.User;
import com.example.exp0911.repository.UserRepository;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @BeforeEach
    public void tearDown() {
        userRepository.deleteAll();
    }

    @Nested
    @DisplayName("[accountUser] 사용자 생성")
    class accountUser {
        private String email;
        private String password;

        @BeforeEach
        void setup() {
            email = "test@gmail.com";
            password = "test1234";
        }

        @Nested
        @DisplayName("정상 케이스")
        class SuccessCase {
            @Test
            @DisplayName("새로운 사용자 생성")
            void accountUserSuccess1() {
                // given
                UserCreateReqDto userCreateReqDto = new UserCreateReqDto(email, password);
                User user = User.createUser(email, password);
                when(userRepository.save(any(User.class))).thenReturn(user);

                //when
                IdDto result = userService.accountUser(userCreateReqDto);

                //then
                Assertions.assertEquals(result.getId(), user.getId(), "생성된 사용자 id가 일치하지 않습니다.");
                verify(userRepository, times(1)).save(any(User.class)); // 호출 되엇는지
            }
        }

        @Nested
        @DisplayName("비정상 케이스")
        class FailCase {
            @Test
            @DisplayName("이미 존재하는 사용자 일 경우")
            void accountUserFail1() {
                // given
                UserCreateReqDto userCreateReqDto = new UserCreateReqDto(email, password);
                when(userRepository.existsByEmail(email)).thenReturn(true);

                // then
                assertThrows(Exception.class, ()-> userService.accountUser(userCreateReqDto));
                verify(userRepository, times(0)).save(any(User.class)); // 호출 되면 안됨
            }
            @Test
            @DisplayName("데이터가 null인 경우")
            void accountUserFail2() {
                Assertions.assertThrows(NullPointerException.class, () -> userService.accountUser(null));
                verify(userRepository, times(0)).save(any(User.class)); // 호출 되면 안됨
            }
        }
    }

    @Nested
    @DisplayName("[findUser] 사용자 조회")
    class findUser {
        private Long id;
        private String email;
        private String password;

        @BeforeEach
        void setup() {
            id = 1L;
            email = "test@gmail.com";
            password = "test1234";
        }

        @Nested
        @DisplayName("정상 케이스")
        class SuccessCase {
            @Test
            @DisplayName("사용자 조회")
            void findUserSuccess1() throws Exception {
                //given
                User user = User.createUser(email, password);
                when(userRepository.findById(eq(id))).thenReturn(Optional.of(user));

                //when
                UserResDto findUser = userService.findUser(id);

                //then
                Assertions.assertEquals(findUser.getEmail(), user.getEmail(), "생성된 사용자의 정보가 일치하지 않습니다.");
                verify(userRepository, times(1)).findById(eq(id));
            }
        }

        @Nested
        @DisplayName(("비정상 케이스"))
        class FailCase {
            @Test
            @DisplayName("존재 하지 않는 사용자의 경우")
            void findUserFail1() throws Exception {
                //given
                when(userRepository.findById(eq(id))).thenReturn(Optional.empty());

                //then
                assertThrows(Exception.class, ()-> userService.findUser(id));
                verify(userRepository, times(1)).findById(eq(id));
            }
        }
    }
}