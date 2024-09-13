package com.spring.mybatis.service;

import com.spring.mybatis.dto.BookDto;
import com.spring.mybatis.entity.Book;
import com.spring.mybatis.mapper.BookMapper;
import com.spring.mybatis.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    //private final BookMapper bookMapper;

    @Autowired
    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        //this.bookMapper = bookMapper;
        this.bookRepository = bookRepository;
    }

    public List<Book> getAllBooks() {
        //return bookMapper.getAllBooks();
        return bookRepository.findAll();
    }

    public Book findById(String id) {
        //return bookMapper.findById(id);
        return bookRepository.findById(id).orElse(null);
    }

    public void insertBook(Book book) {
        //bookMapper.insertBook(book);
        bookRepository.save(book);
    }

    public void updateBook(String id, Book book) {
//        book.setId(id);
//        bookMapper.updateBook(book);
        if(bookRepository.existsById(id)) {
            book.setId(id);
            bookRepository.save(book);
        }
    }

    public void deleteBook(String id) {
        //bookMapper.deleteBook(id);
        bookRepository.deleteById(id);
    }
}
