package com.example.restapi_ex.mapper;

import com.example.restapi_ex.vo.Book;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface BookMapper {
    List<Book> findByPage(@Param("limit") Integer limit ,@Param("offset") Integer offset);
    Integer save(@Param("book") Book book);
    Integer update(@Param("book") Book book);
    void delete(@Param("book") Book book);
}
