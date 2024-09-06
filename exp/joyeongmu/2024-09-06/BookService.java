package com.example.exp0906.service;

import com.example.exp0906.domain.Book;
import com.example.exp0906.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BookService {

    private final BookRepository bookRepository;

    @Transactional
    public Long create(Book book) {
        return bookRepository.save(book).getId();
    }

    public Book detail(Long id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("책을 찾을 수 없습니다."));
    }

    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    @Transactional
    public Long update(Long id, Book book) {
        Book findBook = bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("책을 찾을 수 없습니다."));
        findBook.updateBook(
                book.getTitle(),
                book.getAuthor(),
                book.getPublishedYear(),
                book.getGenre());
        return findBook.getId();
    }

    @Transactional
    public void delete(Long id) {
        bookRepository.deleteById(id);
    }

}
