package com.example.exp0911;

import com.example.exp0911.controller.dto.request.BookCreateReqDto;
import com.example.exp0911.controller.dto.request.BookUpdateReqDto;
import com.example.exp0911.repository.BookRepository;
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
public class BookIntegrationTest {

    @Autowired
    private BookRepository bookRepository;

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
        bookRepository.deleteAll();
    }

    @Test
    @DisplayName("[Books] 도서 생성 테스트")
    void 도서_생성_테스트() throws Exception {
        //given
        BookCreateReqDto bookReqDto = new BookCreateReqDto("testBook", "testAuthor", 1234, "testGenre");
        //when
        ResultActions actions = mockMvc.perform(
                post("/books")
                        .contentType(APPLICATION_JSON)
                        .accept(APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(bookReqDto))
        );

        //then
        actions
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.success").value("true"))
                .andExpect(jsonPath("$.status").value("201"))
                .andExpect(jsonPath("$.response.id").isNotEmpty())

                .andDo(document("Books-save",
                        getRequestPreProcessor(),
                        getResponsePreProcessor(),
                        requestFields(
                                fieldWithPath("title").description("Title for book").type(JsonFieldType.STRING),
                                fieldWithPath("author").description("Author for Book").type(JsonFieldType.STRING),
                                fieldWithPath("publishedYear").description("PublishedYear for Book").type(JsonFieldType.NUMBER),
                                fieldWithPath("genre").description("Genre for Book").type(JsonFieldType.STRING)
                        ),
                        responseFields(
                                fieldWithPath("success").description("성공 유무"),
                                fieldWithPath("status").description("status"),
                                fieldWithPath("response.id").description("Id for Book")
                        )
                ))
                .andDo(print());
    }

    @Test
    @DisplayName("[Books] 도서 상세 조회 테스트")
    void 도서_상세_조회_테스트() throws Exception{
        //given
        BookCreateReqDto bookReqDto = new BookCreateReqDto("testBook", "testAuthor", 1234, "testGenre");
        MvcResult result = mockMvc.perform(
                        post("/books")
                                .contentType(APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(bookReqDto))
                )
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.success").isNotEmpty())
                .andExpect(jsonPath("$.status").isNotEmpty())
                .andExpect(jsonPath("$.response.id").isNotEmpty())
                .andDo(print())
                .andReturn();

        JsonNode jsonNode = objectMapper.readTree(result.getResponse().getContentAsString());
        Long bookId = jsonNode.get("response").get("id").asLong();

        //when
        ResultActions actions = mockMvc.perform(get("/books/{bookId}", bookId)
                .contentType(APPLICATION_JSON)
        );

        //then
        actions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value("true"))
                .andExpect(jsonPath("$.status").value("200"))
                .andExpect(jsonPath("$.response.title").value("testBook"))
                .andExpect(jsonPath("$.response.author").value("testAuthor"))
                .andExpect(jsonPath("$.response.publishedYear").value(1234))
                .andExpect(jsonPath("$.response.genre").value("testGenre"))

                .andDo(document("Books-findById",
                        getRequestPreProcessor(),
                        getResponsePreProcessor(),
                        pathParameters(parameterWithName("bookId").description("bookId for findBy id")),
                        responseFields(
                                fieldWithPath("success").description("성공 유무").type(JsonFieldType.BOOLEAN),
                                fieldWithPath("status").description("status").type(JsonFieldType.NUMBER),
                                fieldWithPath("response.title").description("도서 제목").type(JsonFieldType.STRING),
                                fieldWithPath("response.author").description("도서 저자").type(JsonFieldType.STRING),
                                fieldWithPath("response.publishedYear").description("도서 출판일").type(JsonFieldType.NUMBER),
                                fieldWithPath("response.genre").description("도서 장르").type(JsonFieldType.STRING)
                        )))
                .andDo(print());
    }

    @Test
    @DisplayName("[Books] 도서 수정 테스트")
    public void 도서_수정_테스트() throws Exception {
        //given
        BookCreateReqDto bookReqDto = new BookCreateReqDto("testBook", "testAuthor", 1234, "testGenre");
        MvcResult result = mockMvc.perform(
                        post("/books")
                                .contentType(APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(bookReqDto))
                )
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.success").isNotEmpty())
                .andExpect(jsonPath("$.status").isNotEmpty())
                .andExpect(jsonPath("$.response.id").isNotEmpty())
                .andDo(print())
                .andReturn();

        JsonNode jsonNode = objectMapper.readTree(result.getResponse().getContentAsString());
        Long bookId = jsonNode.get("response").get("id").asLong();

        BookUpdateReqDto bookUpdateReqDto = new BookUpdateReqDto("updateBook", "updateAuthor", 9876, "updateGenre");

        //when
        ResultActions actions = mockMvc.perform(
                put("/books/{bookId}", bookId)
                        .contentType(APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(bookUpdateReqDto))
        );

        //then
        actions
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.success").value("true"))
                .andExpect(jsonPath("$.status").value("201"))
                .andExpect(jsonPath("$.response.id").isNotEmpty())

                .andDo(document("Books-update",
                        getRequestPreProcessor(),
                        getResponsePreProcessor(),
                        pathParameters(parameterWithName("bookId").description("bookId for findBy id")),
                        requestFields(
                                fieldWithPath("title").description("Update Title for book").type(JsonFieldType.STRING),
                                fieldWithPath("author").description("Update Author for Book").type(JsonFieldType.STRING),
                                fieldWithPath("publishedYear").description("Update PublishedYear for Book").type(JsonFieldType.NUMBER),
                                fieldWithPath("genre").description("Update Genre for Book").type(JsonFieldType.STRING)
                        ),
                        responseFields(
                                fieldWithPath("success").description("성공 유무").type(JsonFieldType.BOOLEAN),
                                fieldWithPath("status").description("status").type(JsonFieldType.NUMBER),
                                fieldWithPath("response.id").description("Id for Book").type(JsonFieldType.NUMBER)
                        )))
                .andDo(print());
    }

    @Test
    @DisplayName("[Books] 도서 삭제 테스트")
    public void 도서_삭제_테스트() throws Exception {
        //given
        BookCreateReqDto bookReqDto = new BookCreateReqDto("testBook", "testAuthor", 1234, "testGenre");
        MvcResult result = mockMvc.perform(
                        post("/books")
                                .contentType(APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(bookReqDto))
                )
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.success").isNotEmpty())
                .andExpect(jsonPath("$.status").isNotEmpty())
                .andExpect(jsonPath("$.response.id").isNotEmpty())
                .andDo(print())
                .andReturn();

        JsonNode jsonNode = objectMapper.readTree(result.getResponse().getContentAsString());
        Long bookId = jsonNode.get("response").get("id").asLong();

        //when
        ResultActions actions = mockMvc.perform(
                delete("/books/{bookId}", bookId)
                        .contentType(APPLICATION_JSON)
        );

        //then
        actions
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.success").value("true"))
                .andExpect(jsonPath("$.status").value("201"))
                .andDo(document("Books-delete",
                        getRequestPreProcessor(),
                        getResponsePreProcessor(),
                        pathParameters(parameterWithName("bookId").description("bookId for findBy id")),
                        responseFields(
                                fieldWithPath("success").description("성공 유무").type(JsonFieldType.BOOLEAN),
                                fieldWithPath("status").description("status").type(JsonFieldType.NUMBER),
                                fieldWithPath("response").description("NULL").type(JsonFieldType.NULL)
                        )))
                .andDo(print());
    }
}
