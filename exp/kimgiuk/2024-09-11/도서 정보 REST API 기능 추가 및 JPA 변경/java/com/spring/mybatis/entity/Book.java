package com.spring.mybatis.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import java.util.Date;

@Entity
@Data
public class Book {

    @Id
    private String id;
    private String title;
    private String author;
    private String publisher;
    private Date publishedDate;
}
