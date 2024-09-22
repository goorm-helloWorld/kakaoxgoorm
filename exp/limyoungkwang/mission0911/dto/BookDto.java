package com.example.restapi_ex.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDate;
import java.util.Date;

@Data
public class BookDto {
    private String id;
    private String title;
    private String author;
    private String publisher;
}
