### RestDocs + 통합 테스트

```java
package com.carumuch.capstone.user.controller;

import com.carumuch.capstone.auth.dto.TokenDto;
import com.carumuch.capstone.auth.service.AuthService;
import com.carumuch.capstone.user.domain.User;
import com.carumuch.capstone.user.dto.UserJoinReqDto;
import com.carumuch.capstone.user.dto.UserUpdateReqDto;
import com.carumuch.capstone.user.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.Cookie;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.restdocs.snippet.Attributes;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;


import static com.carumuch.capstone.utility.ApiDocumentUtils.getRequestPreProcessor;
import static com.carumuch.capstone.utility.ApiDocumentUtils.getResponsePreProcessor;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.*;

//import static com.epages.restdocs.apispec.MockMvcRestDocumentationWrapper.*; // swagger version
import static org.springframework.restdocs.cookies.CookieDocumentation.cookieWithName;
import static org.springframework.restdocs.cookies.CookieDocumentation.requestCookies;
import static org.springframework.restdocs.headers.HeaderDocumentation.headerWithName;
import static org.springframework.restdocs.headers.HeaderDocumentation.requestHeaders;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.*; // restDocs version

import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(RestDocumentationExtension.class)
class UserControllerTest {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthService authService;

    private MockMvc mockMvc;

    @BeforeEach
    void setUpMockMvcForRestDocs(WebApplicationContext webApplicationContext,
                                 RestDocumentationContextProvider restDocumentationContextProvider) {
        this.mockMvc = MockMvcBuilders
                .webAppContextSetup(webApplicationContext)
                .apply(documentationConfiguration(restDocumentationContextProvider))
                .apply(springSecurity())
                .build();
    }

    private ObjectMapper objectMapper = new ObjectMapper();

    @Test
    @DisplayName("[Users] 아이디 중복 테스트")
    void 아이디_중복_테스트() throws Exception {
        //given
        String loginId = "check";

        //when
        ResultActions actions = mockMvc.perform(
                get("/users/check-login-id/" + loginId)
                        .contentType(APPLICATION_JSON)
        );

        //then
        actions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").isNotEmpty())
                .andExpect(jsonPath("$.status").isNotEmpty())
                .andExpect(jsonPath("$.code").isNotEmpty())
                .andExpect(jsonPath("$.message").isNotEmpty())
                .andExpect(jsonPath("$.response").isNotEmpty())

                .andDo(document("users-check-loginId",
                        getRequestPreProcessor(),
                        getResponsePreProcessor(),
                        responseFields(
                                fieldWithPath("success").description("사용 가능한 아이디"),
                                fieldWithPath("status").description("status"),
                                fieldWithPath("code").description("code"),
                                fieldWithPath("message").description("결과 메세지"),
                                fieldWithPath("response").description("null")
                        )
                ))
                .andDo(print());
    }

    @Test
    @DisplayName("[Users] 이메일 중복 테스트")
    void 이메일_중복_테스트() throws Exception {
        //given
        String email = "check@gmail.com";

        //when
        ResultActions actions = mockMvc.perform(
                get("/users/check-email/" + email)
                        .contentType(APPLICATION_JSON)
        );

        //then
        actions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").isNotEmpty())
                .andExpect(jsonPath("$.status").isNotEmpty())
                .andExpect(jsonPath("$.code").isNotEmpty())
                .andExpect(jsonPath("$.message").isNotEmpty())
                .andExpect(jsonPath("$.response").isNotEmpty())

                .andDo(document("users-check-email",
                        getRequestPreProcessor(),
                        getResponsePreProcessor(),
                        responseFields(
                                fieldWithPath("success").description("사용 가능한 이메일"),
                                fieldWithPath("status").description("status"),
                                fieldWithPath("code").description("code"),
                                fieldWithPath("message").description("결과 메세지"),
                                fieldWithPath("response").description("null")
                        )
                ))
                .andDo(print());
    }

    @Test
    @DisplayName("[Users] 회원가입 테스트")
    void 회원가입_테스트() throws Exception {
        //given
        UserJoinReqDto userJoinReqDto = new UserJoinReqDto(
                "test",
                "helloworld1234@",
                "test@gmail.com",
                "테스트");
        //when
        ResultActions actions = mockMvc.perform(
                post("/users/signup")
                        .contentType(APPLICATION_JSON)
                        .accept(APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userJoinReqDto))
        );

        //then
        actions
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.success").isNotEmpty())
                .andExpect(jsonPath("$.status").isNotEmpty())
                .andExpect(jsonPath("$.code").isNotEmpty())
                .andExpect(jsonPath("$.message").isNotEmpty())
                .andExpect(jsonPath("$.response").isNotEmpty())

                .andDo(document("users-join",
                        getRequestPreProcessor(),
                        getResponsePreProcessor(),
                        requestFields(
                                fieldWithPath("loginId").description("사용자 아이디").type(JsonFieldType.STRING),
                                fieldWithPath("password").description("사용자 비밀번호").type(JsonFieldType.STRING),
                                fieldWithPath("email").description("사용자 이메일").type(JsonFieldType.STRING),
                                fieldWithPath("name").description("사용자 이름").type(JsonFieldType.STRING)
                        ),
                        responseFields(
                                fieldWithPath("success").description("성공 유무"),
                                fieldWithPath("status").description("status"),
                                fieldWithPath("code").description("code"),
                                fieldWithPath("message").description("결과 메세지"),
                                fieldWithPath("response").description("사용자 userId")
                        )
                ))
                .andDo(print());
    }

    @Test
    @DisplayName("[Users] 회원 상세 조회 테스트")
    void 회원_상세_조회_테스트() throws Exception{
        //given
        User user = userRepository.findById(1001L).get();
        TokenDto tokenDto = authService.generateToken("SERVER", user.getLoginId(), user.getRole().getKey());

        //when
        ResultActions actions = mockMvc.perform(
                get("/users")
                        .header("Authorization", "Bearer " + tokenDto.getAccessToken())
                        .cookie(new Cookie("refresh-token", tokenDto.getRefreshToken()))
                        .contentType(APPLICATION_JSON)
        );

        //then
        actions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.status").value(200))
                .andExpect(jsonPath("$.response.loginId").value("test1"))
                .andExpect(jsonPath("$.response.email").value("test1@gmail.com"))
                .andExpect(jsonPath("$.response.name").value("테스트"))

                .andDo(document("users-info",
                        getRequestPreProcessor(),
                        getResponsePreProcessor(),
                        requestHeaders(
                                headerWithName("Authorization")
                                        .description("JWT 토큰")
                                        .attributes(Attributes.key("constraints")
                                                .value("JWT Form")).optional()
                        ),
                        requestCookies(
                                cookieWithName("refresh-token")
                                        .description("리프레시 토큰")
                                        .attributes(Attributes.key("constraints").value("JWT Refresh Token"))
                                        .optional()
                        ),
                        responseFields(
                                fieldWithPath("success").description("성공 유무").type(JsonFieldType.BOOLEAN),
                                fieldWithPath("status").description("status").type(JsonFieldType.NUMBER),
                                fieldWithPath("code").description("code").type(JsonFieldType.STRING),
                                fieldWithPath("message").description("결과 메세지").type(JsonFieldType.STRING),
                                fieldWithPath("response.loginId").description("회원 아이디").type(JsonFieldType.STRING),
                                fieldWithPath("response.email").description("회원 이메일").type(JsonFieldType.STRING),
                                fieldWithPath("response.name").description("회원 이름").type(JsonFieldType.STRING)
                        )))
                .andDo(print());
    }

    @Test
    @DisplayName("[Users] 회원 탈퇴 테스트")
    void 회원_탈퇴_테스트() throws Exception{

        //given
        User user = userRepository.findById(1002L).get();
        TokenDto tokenDto = authService.generateToken("SERVER", user.getLoginId(), user.getRole().getKey());

        //when
        ResultActions actions = mockMvc.perform(
                delete("/users")
                        .header("Authorization", "Bearer " + tokenDto.getAccessToken())
                        .cookie(new Cookie("refresh-token", tokenDto.getRefreshToken()))
                        .contentType(APPLICATION_JSON)
        );

        //then
        actions
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.status").value(201))

                .andDo(document("users-delete",
                        getRequestPreProcessor(),
                        getResponsePreProcessor(),
                        requestHeaders(
                                headerWithName("Authorization")
                                        .description("JWT 토큰")
                                        .attributes(Attributes.key("constraints")
                                                .value("JWT Form")).optional()
                        ),
                        requestCookies(
                                cookieWithName("refresh-token")
                                        .description("리프레시 토큰")
                                        .attributes(Attributes.key("constraints").value("JWT Refresh Token"))
                                        .optional()
                        ),
                        responseFields(
                                fieldWithPath("success").description("성공 유무").type(JsonFieldType.BOOLEAN),
                                fieldWithPath("status").description("status").type(JsonFieldType.NUMBER),
                                fieldWithPath("code").description("code").type(JsonFieldType.STRING),
                                fieldWithPath("message").description("결과 메세지").type(JsonFieldType.STRING),
                                fieldWithPath("response").description("null").type(JsonFieldType.NULL)
                        )))
                .andDo(print());
    }

    @Test
    @DisplayName("[Users] 회원 수정 테스트")
    void 회원_수정_테스트() throws Exception{

        //given
        UserUpdateReqDto userUpdateReqDto = new UserUpdateReqDto("update@gmail.com", "수정");

        User user = userRepository.findById(1003L).get();
        TokenDto tokenDto = authService.generateToken("SERVER", user.getLoginId(), user.getRole().getKey());

        //when
        ResultActions actions = mockMvc.perform(
                put("/users")
                        .header("Authorization", "Bearer " + tokenDto.getAccessToken())
                        .cookie(new Cookie("refresh-token", tokenDto.getRefreshToken()))
                        .contentType(APPLICATION_JSON)
                        .accept(APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userUpdateReqDto))
        );

        //then
        actions
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.status").value(201))

                .andDo(document("users-update",
                        getRequestPreProcessor(),
                        getResponsePreProcessor(),
                        requestHeaders(
                                headerWithName("Authorization")
                                        .description("JWT 토큰")
                                        .attributes(Attributes.key("constraints")
                                                .value("JWT Form")).optional()
                        ),
                        requestCookies(
                                cookieWithName("refresh-token")
                                        .description("리프레시 토큰")
                                        .attributes(Attributes.key("constraints").value("JWT Refresh Token"))
                                        .optional()
                        ),
                        responseFields(
                                fieldWithPath("success").description("성공 유무").type(JsonFieldType.BOOLEAN),
                                fieldWithPath("status").description("status").type(JsonFieldType.NUMBER),
                                fieldWithPath("code").description("code").type(JsonFieldType.STRING),
                                fieldWithPath("message").description("결과 메세지").type(JsonFieldType.STRING),
                                fieldWithPath("response").description("1003").type(JsonFieldType.NUMBER)
                        )))
                .andDo(print());
    }
}
```

```asciidoc
= FOSS REST Docs - Users API
:doctype: book
:icons: font
:source-highlighter: highlightjs
:toc: left
:toclevels: 1

== Users API Overview

본 문서는 **Users** 그룹에 속하는 API에 대한 설명과 예시를 포함합니다. 이 API는 사용자 관련 기능을 제공하며, 다음과 같은 주요 기능을 포함합니다:

- 아이디 중복 체크
- 이메일 중복 체크
- 회원가입
- 사용자 정보 조회
- 사용자 정보 업데이트
- 사용자 삭제

=== Endpoints

- `/check-login-id/{loginId}` : 아이디 중복 체크
- `/check-email/{email}` : 이메일 중복 체크
- `/users/signup` : 회원가입
- `/users` : 사용자 정보 조회, 업데이트, 삭제

== API Documentation

[[users-check-loginId]]
=== [GET] /check-login-id/{loginId} - 아이디 중복 체크
operation::users-check-loginId[]
**설명**: 입력된 `loginId`가 중복되었는지 확인합니다.
- 인증 불필요
- 성공 시, 중복 여부를 반환합니다.

[[users-check-email]]
=== [GET] /check-email/{email} - 이메일 중복 체크
operation::users-check-email[]
**설명**: 입력된 `email`이 중복되었는지 확인합니다.
- 인증 불필요
- 성공 시, 중복 여부를 반환합니다.

[[users-signup]]
=== [POST] /users/signup - 회원가입
operation::users-join[]
**설명**: 새로운 사용자를 등록합니다.
- 인증 불필요
- 성공 시, 사용자 ID를 반환합니다.

[[users-info]]
=== [GET] /users - 사용자 정보 조회
operation::users-info[]
**설명**: 현재 인증된 사용자의 정보를 조회합니다.
- 인증 필요
- JWT 토큰을 통한 인증

[[users-update]]
=== [PUT] /users - 사용자 정보 업데이트
operation::users-update[]
**설명**: 현재 인증된 사용자의 정보를 수정합니다.
- 인증 필요
- JWT 토큰을 통한 인증

[[users-delete]]
=== [DELETE] /users - 사용자 삭제
operation::users-delete[]
**설명**: 특정 사용자를 삭제합니다.
- 인증 필요
- 관리자 권한 필요

```