package com.spring.mybatis.service;

import com.spring.mybatis.dto.BookDto;
import com.spring.mybatis.mapper.BookMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class BookService {
    @Autowired
    private final BookMapper bookMapper;

    public BookService(BookMapper bookMapper) {
        this.bookMapper = bookMapper;
    }

    public List<BookDto> getAllBooks() {
        return bookMapper.getAllBooks();
    }

    public List<BookDto> searchBooks(String title, String author) {
        return bookMapper.searchBooks(title, author);
    }
}
