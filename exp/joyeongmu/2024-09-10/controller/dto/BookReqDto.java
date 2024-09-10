package com.example.restapi_ex.controller.dto;

import com.example.restapi_ex.vo.Book;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class BookReqDto {
    private Long id;
    private String title;
    private String author;
    private String publisher;

    public Book getBook() {
        return new Book(
                this.id,
                this.title,
                this.author,
                this.publisher
        );
    }
}
