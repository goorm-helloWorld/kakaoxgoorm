package com.example.restapi_ex.controller;

import com.example.restapi_ex.controller.dto.BookReqDto;
import com.example.restapi_ex.controller.dto.BookResDto;
import com.example.restapi_ex.service.BookService;
import com.example.restapi_ex.vo.Book;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @Operation(summary = "도서 리스트", description = "**성공 데이터:**  `BookList`")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "도서 리스트 조회 성공"),
            @ApiResponse(responseCode = "500", description = "사버 오류"),
    })
    @GetMapping("/books")
    public ResponseEntity<?> getBookList(@RequestParam Integer page) {
        List<Book> bookList = bookService.getBookByPage(page, 3);

        List<BookResDto> bookResponseDtoList = new ArrayList<>();
        for (Book book : bookList) {
            bookResponseDtoList.add(new BookResDto(book));
        }
        return ResponseEntity.status(HttpStatus.OK).body(bookResponseDtoList);
    }

    @Operation(summary = "도서 생성", description = "**성공 데이터:**  `success`")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "도서 생성 성공"),
            @ApiResponse(responseCode = "500", description = "사버 오류"),
    })
    @PostMapping("/books")
    public ResponseEntity<?> createBook(@RequestBody BookReqDto bookReqDto) {
        Book book = bookReqDto.getBook();
        bookService.saveBook(book);
        return ResponseEntity.status(HttpStatus.CREATED).body("success");
    }

    @Operation(summary = "도서 업데이트", description = "**성공 데이터:**  `success`")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "도서 업데이트 성공"),
            @ApiResponse(responseCode = "500", description = "사버 오류"),
    })
    @PutMapping("/books")
    public ResponseEntity<?> updateBook(@RequestBody BookReqDto bookReqDto) {
        Book book = bookReqDto.getBook();
        bookService.updateBook(book);
        return ResponseEntity.status(HttpStatus.CREATED).body("success");
    }

    @Operation(summary = "도서 삭제", description = "**성공 데이터:**  `success`")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "도서 삭제 성공"),
            @ApiResponse(responseCode = "500", description = "사버 오류"),
    })
    @DeleteMapping("/books")
    public ResponseEntity<?> deleteBook(@RequestBody BookReqDto bookReqDto) {
        Book book = bookReqDto.getBook();
        bookService.deleteBook(book);
        return ResponseEntity.status(HttpStatus.CREATED).body("success");
    }
}
