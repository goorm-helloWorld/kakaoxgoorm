package com.spring.springbootex2.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String author;
    private int pubishedYear;
    private String genre;

    public Book() {}

    public Book(String title, String author, int pubishedYear, String genre) {
        this.title = title;
        this.author = author;
        this.pubishedYear = pubishedYear;
        this.genre = genre;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setPubishedYear(int pubishedYear) {
        this.pubishedYear = pubishedYear;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getAuthor() {
        return author;
    }

    public int getPubishedYear() {
        return pubishedYear;
    }

    public String getGenre() {
        return genre;
    }
}
