package com.example.restapi_ex.service;

import com.example.restapi_ex.dto.BookDto;
import com.example.restapi_ex.dto.ItemDto;
import com.example.restapi_ex.mapper.BookMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Service
@Slf4j
public class BookService {

    private final BookMapper bookMapper;

    public BookService(BookMapper bookMapper) {
        this.bookMapper = bookMapper;
    }

//    public BookDto getBook(String id) {
//        HashMap<String, Object> paramMap = new HashMap<>();
//        paramMap.put("id", id);
//
//        HashMap<String, Object> res = bookMapper.findBookById(paramMap);
//
//        BookDto bookDto = new BookDto();
//        bookDto.setId((String)res.get("ID"));
//        bookDto.setAuthor((String)res.get("AUTHOR"));
//        bookDto.setTitle((String)res.get("TITLE"));
//        bookDto.setPublisher((String)res.get("PUBLISHER"));
//        bookDto.setPublished_date((Date) res.get("PUBLISHER_DATE"));
//
//        return bookDto;
//    }
    public BookDto getBook(String id) {
        return bookMapper.findBookById(id);
    }

    public List<BookDto> getAllBooks() {
        return bookMapper.findAll();
    }

}
