package org.example.domain.myboard.board.controller;

import lombok.RequiredArgsConstructor;
import org.example.domain.myboard.board.dto.BoardCreateReqDto;
import org.example.domain.myboard.board.service.BoardService;
import org.example.global.dto.ResponseDto;
import org.example.global.validation.ValidationSequence;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/boards")
public class BoardController {
    private final BoardService boardService;

    @PostMapping
    public ResponseEntity<?> createBoard(@Validated(ValidationSequence.class) @RequestBody BoardCreateReqDto boardCreateReqDto) {
        boardService.create(boardCreateReqDto);
        return ResponseEntity.status(CREATED).body(ResponseDto.success(CREATED, null));
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllBoard() {
        return ResponseEntity.status(OK).body(ResponseDto.success(OK, boardService.getAllBoard()));
    }

    @GetMapping("/{boardId}/posts")
    public ResponseEntity<?> getBoardPosts(@PathVariable("boardId") Long id) {
        return ResponseEntity.status(OK).body(ResponseDto.success(OK, boardService.findAllPostsByBoardId(id)));
    }
}
