package com.example.exp0911.controller;

import com.example.exp0911.controller.dto.request.UserCreateReqDto;
import com.example.exp0911.repository.UserRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
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
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;


import static com.example.exp0911.utility.ApiDocumentUtils.getRequestPreProcessor;
import static com.example.exp0911.utility.ApiDocumentUtils.getResponsePreProcessor;
import static org.springframework.http.MediaType.*;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.*;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(RestDocumentationExtension.class)
class UserControllerTest {

    @Autowired
    private UserRepository userRepository;

    private MockMvc mockMvc;

    @BeforeEach
    void setUpMockMvcForRestDocs(WebApplicationContext webApplicationContext,
                                 RestDocumentationContextProvider restDocumentationContextProvider) {
        this.mockMvc = MockMvcBuilders
                .webAppContextSetup(webApplicationContext)
                .apply(documentationConfiguration(restDocumentationContextProvider))
                .build();
    }

    private ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    public void tearDown() {
        userRepository.deleteAll();
    }

    @Test
    @DisplayName("[Users] 사용자 생성 테스트")
    void 사용자_생성_테스트() throws Exception {
        //given
        UserCreateReqDto userCreateReqDto = new UserCreateReqDto("test@gmail.com", "test1234");

        //when
        ResultActions actions = mockMvc.perform(
                post("/users")
                        .contentType(APPLICATION_JSON)
                        .accept(APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userCreateReqDto))
        );

        //then
        actions
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.success").value("true"))
                .andExpect(jsonPath("$.status").value("201"))
                .andExpect(jsonPath("$.response.id").isNotEmpty())

                .andDo(document("Users-save",
                        getRequestPreProcessor(),
                        getResponsePreProcessor(),
                        requestFields(
                                fieldWithPath("email").description("사용자 이메일").type(JsonFieldType.STRING),
                                fieldWithPath("password").description("사용자 비밀번호").type(JsonFieldType.STRING)
                        ),
                        responseFields(
                                fieldWithPath("success").description("성공 유무"),
                                fieldWithPath("status").description("status"),
                                fieldWithPath("response.id").description("user_id")
                        )
                ))
                .andDo(print());
    }

    @Test
    @DisplayName("[Users] 사용자 상세 조회 테스트")
    void 사용자_상세_조회_테스트() throws Exception{
        //given
        UserCreateReqDto userCreateReqDto = new UserCreateReqDto("test@gmail.com", "test1234");
        MvcResult result = mockMvc.perform(
                        post("/users")
                                .contentType(APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(userCreateReqDto))
                )
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.success").isNotEmpty())
                .andExpect(jsonPath("$.status").isNotEmpty())
                .andExpect(jsonPath("$.response.id").isNotEmpty())
                .andDo(print())
                .andReturn();

        JsonNode jsonNode = objectMapper.readTree(result.getResponse().getContentAsString());
        Long userId = jsonNode.get("response").get("id").asLong();

        //when
        ResultActions actions = mockMvc.perform(
                get("/users/{userId}", userId)
                .contentType(APPLICATION_JSON)
        );

        //then
        actions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value("true"))
                .andExpect(jsonPath("$.status").value("200"))
                .andExpect(jsonPath("$.response.email").value("test@gmail.com"))

                .andDo(document("Users-findById",
                        getRequestPreProcessor(),
                        getResponsePreProcessor(),
                        pathParameters(parameterWithName("userId").description("user_id")),
                        responseFields(
                                fieldWithPath("success").description("성공 유무").type(JsonFieldType.BOOLEAN),
                                fieldWithPath("status").description("status").type(JsonFieldType.NUMBER),
                                fieldWithPath("response.email").description("사용자 이메일").type(JsonFieldType.STRING)
                        )))
                .andDo(print());
    }
}