package com.example.restapi_ex.repository;

import com.example.restapi_ex.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
