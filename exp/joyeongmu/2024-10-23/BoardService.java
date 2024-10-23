package org.example.domain.myboard.board.service;

import lombok.RequiredArgsConstructor;
import org.example.domain.myboard.board.db.Board;
import org.example.domain.myboard.board.db.BoardRepository;
import org.example.domain.myboard.board.dto.BoardCreateReqDto;
import org.example.domain.myboard.board.dto.BoardViewResDto;
import org.example.domain.post.db.PostRepository;
import org.example.domain.post.dto.PostViewResDto;
import org.example.global.exception.CustomException;
import org.example.global.exception.ErrorCode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static org.example.global.exception.ErrorCode.*;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BoardService {
    private final BoardRepository boardRepository;
    private final PostRepository postRepository;

    @Transactional
    public void create(BoardCreateReqDto reqDto) {
        boardRepository.save(Board.of(
                reqDto.getName(),
                Board.Status.REGISTERED.getKey()
        ));
    }

    public List<BoardViewResDto> getAllBoard() {
        return boardRepository.findAll().stream()
                .map(i -> new BoardViewResDto(i.getId(),i.getName(),i.getStatus()))
                .collect(Collectors.toList());
    }

    public List<PostViewResDto> findAllPostsByBoardId(Long id) {
        return postRepository.findAllByBoardIdOrderByIdDesc(id).stream()
                .map(PostViewResDto::new)
                .collect(Collectors.toList());
    }
}
