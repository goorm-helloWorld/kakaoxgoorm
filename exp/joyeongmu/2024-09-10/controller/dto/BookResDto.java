package com.example.restapi_ex.controller.dto;

import com.example.restapi_ex.vo.Book;
import lombok.Getter;

@Getter
public class BookResDto {
    private final Long id;
    private final String title;
    private final String author;
    private final String publisher;

    public BookResDto(Book book) {
        this.id = book.getId();
        this.title = book.getTitle();
        this.author = book.getAuthor();
        this.publisher = book.getPublisher();
    }
}
