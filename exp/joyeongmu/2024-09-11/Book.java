package com.example.exp0911.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static jakarta.persistence.GenerationType.*;

@Entity
@Table(name = "book")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Book {

    @Id @GeneratedValue(strategy = IDENTITY)
    @Column(name = "book_id")
    private Long id;

    @Column(name = "title", length = 30)
    private String title;

    @Column(name = "author", length = 30)
    private String author;

    @Column(name = "published_year")
    private int publishedYear;

    @Column(name = "genre")
    private String genre;

    @Builder
    private Book(String title, String author, int publishedYear, String genre) {
        this.title = title;
        this.author = author;
        this.publishedYear = publishedYear;
        this.genre = genre;
    }

    /* 생성 */
    public static Book createBook(String title, String author, int publishedYear, String genre) {
        return Book.builder()
                .title(title)
                .author(author)
                .publishedYear(publishedYear)
                .genre(genre)
                .build();
    }

    /* 수정 */
    public void update(String title, String author, int publishedYear, String genre) {
        Book.builder()
                .title(title)
                .author(author)
                .publishedYear(publishedYear)
                .genre(genre)
                .build();
    }
}
