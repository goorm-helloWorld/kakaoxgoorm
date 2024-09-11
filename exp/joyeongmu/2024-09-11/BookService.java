package com.example.exp0911.service;

import com.example.exp0911.controller.dto.IdDto;
import com.example.exp0911.controller.dto.request.BookCreateReqDto;
import com.example.exp0911.controller.dto.request.BookUpdateReqDto;
import com.example.exp0911.controller.dto.response.BookResDto;
import com.example.exp0911.domain.Book;
import com.example.exp0911.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class BookService {

    private final BookRepository bookRepository;

    /* 등록된 도서 인지 확인 */
    private void existsBook(Long id) {
        if (!bookRepository.existsById(id)) {
            throw new RuntimeException("등록되지 않은 도서 입니다.");
        }
    }

    @Transactional
    public IdDto addBook(BookCreateReqDto reqDto) {
        return new IdDto(bookRepository.save(Book.createBook(
                reqDto.getTitle(),
                reqDto.getAuthor(),
                reqDto.getPublishedYear(),
                reqDto.getGenre()))
                .getId());
    }

    public BookResDto findBook(Long id) {
        return new BookResDto(bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("등록되지 않은 도서입니다.")));
    }

    @Transactional
    public IdDto updateBook(Long id, BookUpdateReqDto reqDto) {
        Book findBook = bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("등록되지 않은 도서입니다."));
        findBook.update(
                reqDto.getTitle(),
                reqDto.getAuthor(),
                reqDto.getPublishedYear(),
                reqDto.getGenre()
        );
        return new IdDto(findBook.getId());
    }

    @Transactional
    public void deleteBook(Long id) {
        existsBook(id);
        bookRepository.deleteById(id);
    }
}
