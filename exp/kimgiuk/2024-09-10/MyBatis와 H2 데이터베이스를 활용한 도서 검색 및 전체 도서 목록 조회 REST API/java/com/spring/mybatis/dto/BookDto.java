package com.spring.mybatis.dto;

import lombok.Data;

import java.util.Date;

@Data
public class BookDto {
    private String id;
    private String title;
    private String author;
    private String publisher;
    private Date publishedDate;
}
