package com.example.restapi_ex.controller;

import com.example.restapi_ex.dto.BookDto;
import com.example.restapi_ex.entity.Book;
import com.example.restapi_ex.service.BookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/books")
public class BookController {
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }
//
//    @GetMapping
//    public List<BookDto> getAllBooks() {
//        return bookService.getAllBooks();
//    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity<BookDto> getItem(@RequestParam("id") String id) {
//        BookDto book = bookService.getBook(id);
//        if (book != null) {
//            return ResponseEntity.ok(book);
//        } else {
//            return ResponseEntity.notFound().build();
//        }
//    }
//
//    @PostMapping
//    public ResponseEntity<String> addBook(@RequestBody BookDto book) {
//        bookService.addBook(book);
//        return new ResponseEntity<>("Book added successfully", HttpStatus.CREATED);
//    }
//
//    @PutMapping("/{id}")
//    public ResponseEntity<String> updateBook(@PathVariable String id, @RequestBody BookDto book) {
//        BookDto existingBook = bookService.getBook(id);
//        if (existingBook != null) {
//            bookService.updateBook(id, book);
//            return ResponseEntity.ok("Book updated successfully");
//        } else {
//            return ResponseEntity.notFound().build();
//        }
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<String> deleteBook(@PathVariable String id) {
//        BookDto existingBook = bookService.getBook(id);
//        if (existingBook != null) {
//            bookService.deleteBookById(id);
//            return ResponseEntity.ok("Book deleted successfully");
//        } else {
//            return ResponseEntity.notFound().build();
//        }
//    }
// 1. 전체 도서 목록 조회 (GET /books)
    @GetMapping
    public List<Book> getAllBooks() {
        return bookService.getAllBooks();
    }

    // 2. 도서 검색 (GET /books/{id})
    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Long id) {
        Book book = bookService.getBookById(id);
        if (book != null) {
            return ResponseEntity.ok(book);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // 3. 도서 정보 등록 (POST /books)
    @PostMapping
    public ResponseEntity<String> addBook(@RequestBody Book book) {
        bookService.addBook(book);
        return new ResponseEntity<>("Book added successfully", HttpStatus.CREATED);
    }

    // 4. 도서 정보 업데이트 (PUT /books/{id})
    @PutMapping("/{id}")
    public ResponseEntity<String> updateBook(@PathVariable Long id, @RequestBody Book book) {
        Book existingBook = bookService.getBookById(id);
        if (existingBook != null) {
            bookService.updateBook(id, book);
            return ResponseEntity.ok("Book updated successfully");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // 5. 도서 정보 삭제 (DELETE /books/{id})
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBook(@PathVariable Long id) {
        Book existingBook = bookService.getBookById(id);
        if (existingBook != null) {
            bookService.deleteBookById(id);
            return ResponseEntity.ok("Book deleted successfully");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}