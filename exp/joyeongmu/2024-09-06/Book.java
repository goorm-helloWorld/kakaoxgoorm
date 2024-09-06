package com.example.exp0906.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "book")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_id")
    private Long id;

    @Column(name = "title", length = 100)
    private String title;

    @Column(name = "author", length = 30)
    private String author;

    @Column(name = "published_year", length = 30)
    private int publishedYear;

    @Column(name = "genre", length = 20)
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

    /* 업데이트 */
    public void updateBook(String title, String author, int publishedYear, String genre) {
        this.title = title;
        this.author = author;
        this.publishedYear = publishedYear;
        this.genre = genre;
    }
}
