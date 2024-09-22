package com.example.restapi_ex.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Book {

    @Id
    @GeneratedValue
    private Long id;
    private String title;
    private String author;
    private String publisher;
}
