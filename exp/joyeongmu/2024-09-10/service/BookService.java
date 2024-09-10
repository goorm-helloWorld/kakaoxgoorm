package com.example.restapi_ex.service;

import com.example.restapi_ex.mapper.BookMapper;
import com.example.restapi_ex.vo.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    @Autowired
    private BookMapper bookMapper;

    public List<Book> getBookByPage(Integer page, Integer size) {
        return bookMapper.findByPage(size, (page -1) * size);
    }

    public boolean saveBook(Book book) {
        Integer result = bookMapper.save(book);
        return result == 1;
    }

    public boolean updateBook(Book book) {
        Integer result = bookMapper.update(book);
        return result == 1;
    }

    public void deleteBook(Book book) {
        bookMapper.delete(book);
    }
}
