package com.example.exp0911.controller;

import com.example.exp0911.controller.dto.request.BookCreateReqDto;
import com.example.exp0911.controller.dto.request.BookUpdateReqDto;
import com.example.exp0911.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.example.exp0911.controller.dto.ResponseDto.*;
import static org.springframework.http.HttpStatus.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;

    @PostMapping
    public ResponseEntity<?> bookAdd(@RequestBody BookCreateReqDto reqDto) {
        return ResponseEntity.status(CREATED)
                .body(success(CREATED, bookService.addBook(reqDto)));
    }

    @GetMapping("/{bookId}")
    public ResponseEntity<?> bookDetails(@PathVariable(name = "bookId") Long id) {
        return ResponseEntity.status(OK)
                .body(success(OK, bookService.findBook(id)));
    }

    @PutMapping("/{bookId}")
    public ResponseEntity<?> bookUpdate(@PathVariable(name = "bookId") Long id,
                                        @RequestBody BookUpdateReqDto reqDto) {
        return ResponseEntity.status(CREATED)
                .body(success(CREATED, bookService.updateBook(id, reqDto)));
    }

    @DeleteMapping("/{bookId}")
    public ResponseEntity<?> bookDelete(@PathVariable(name = "bookId") Long id) {
        bookService.deleteBook(id);
        return ResponseEntity.status(CREATED)
                .body(success(CREATED, null));
    }
}
