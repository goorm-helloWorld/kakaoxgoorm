package com.example.exp0911.repository;

import com.example.exp0911.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
    boolean existsById(Long id);
}
