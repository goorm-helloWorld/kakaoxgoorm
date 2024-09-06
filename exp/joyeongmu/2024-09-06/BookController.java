package com.example.exp0906.controller;

import com.example.exp0906.domain.Book;
import com.example.exp0906.service.BookService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Book book) {
        return ResponseEntity.status(CREATED).body(bookService.create(book));
    }

    @GetMapping("/{bookId}")
    public ResponseEntity<?> detail(@PathVariable("bookId") Long id) {
        return ResponseEntity.status(OK).body(bookService.detail(id));
    }

    @GetMapping
    public ResponseEntity<?> findAll() {
        return ResponseEntity.status(OK).body(bookService.findAll());
    }

    @PutMapping("/{bookId}")
    public ResponseEntity<?> update(@PathVariable("bookId") Long id,
                                    @RequestBody Book book) {
        return ResponseEntity.status(CREATED).body(bookService.update(id, book));
    }

    @DeleteMapping("/{bookId}")
    public ResponseEntity<?> delete(@PathVariable("bookId") Long id) {
        bookService.delete(id);
        return ResponseEntity.status(CREATED).body(true);
    }
}
