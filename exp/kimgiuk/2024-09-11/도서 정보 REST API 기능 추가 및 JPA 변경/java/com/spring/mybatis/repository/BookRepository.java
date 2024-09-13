package com.spring.mybatis.repository;

import com.spring.mybatis.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, String> {
}
