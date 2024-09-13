package com.spring.mybatis.mapper;

import com.spring.mybatis.dto.BookDto;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface BookMapper {

    List<BookDto> getAllBooks();

    BookDto findById(String id);

    void insertBook(BookDto book);

    void updateBook(BookDto book);

    void deleteBook(String id);
}
