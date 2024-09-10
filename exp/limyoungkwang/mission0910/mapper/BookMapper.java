package com.example.restapi_ex.mapper;

import com.example.restapi_ex.dto.BookDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;
import java.util.List;

@Mapper
public interface BookMapper {

    //HashMap<String, Object> findBookById(HashMap<String, Object> paramMap);

    BookDto findBookById(String id);

    List<BookDto> findAll();
}
