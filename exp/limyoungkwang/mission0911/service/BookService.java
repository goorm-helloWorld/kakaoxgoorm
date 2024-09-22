package com.example.restapi_ex.service;

import com.example.restapi_ex.dto.BookDto;
import com.example.restapi_ex.dto.ItemDto;
import com.example.restapi_ex.entity.Book;
import com.example.restapi_ex.mapper.BookMapper;
import com.example.restapi_ex.repository.BookRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class BookService {

    //private final BookMapper bookMapper;
    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    // 1. 전체 도서 목록 조회
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    // 2. ID로 도서 조회
    public Book getBookById(Long id) {
        Optional<Book> book = bookRepository.findById(id);
        return book.orElse(null);
    }

    // 3. 도서 정보 등록
    public void addBook(Book book) {
        bookRepository.save(book);
    }

    // 4. 도서 정보 업데이트
    public void updateBook(Long id, Book book) {
        if (bookRepository.existsById(id)) {
            book.setId(id);
            bookRepository.save(book);
        }
    }

    // 5. 도서 정보 삭제
    public void deleteBookById(Long id) {
        bookRepository.deleteById(id);
    }
}
