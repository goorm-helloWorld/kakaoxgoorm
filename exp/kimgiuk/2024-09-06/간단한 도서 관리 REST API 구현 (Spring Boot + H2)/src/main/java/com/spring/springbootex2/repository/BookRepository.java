package com.spring.springbootex2.repository;

import com.spring.springbootex2.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
