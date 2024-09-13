package org.example.mywebservice.controller;

import lombok.RequiredArgsConstructor;
import org.example.mywebservice.dto.request.PostCreateReqDto;
import org.example.mywebservice.dto.request.PostUpdateReqDto;
import org.example.mywebservice.dto.response.IdDto;
import org.example.mywebservice.dto.response.ResponseDto;
import org.example.mywebservice.service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

import static org.example.mywebservice.dto.response.ResponseDto.*;
import static org.springframework.http.HttpStatus.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/posts")
public class PostRestController {

    private final PostService postService;

    @PostMapping
    public ResponseEntity<?> addPost(@RequestBody PostCreateReqDto postCreateReqDto) {
        return ResponseEntity.status(CREATED)
                .body(success(CREATED, new IdDto(postService.addPost(postCreateReqDto).getId())));
    }

    @GetMapping
    public ResponseEntity<?> getAllPosts() {
        return ResponseEntity.status(OK)
                .body(success(OK, postService.getAllPosts()));
    }

    @GetMapping("/{postId}")
    public ResponseEntity<?> getPostById(@PathVariable("postId") Long id) {
        return ResponseEntity.status(OK)
                .body(success(OK, postService.findPostById(id)));
    }

    @PutMapping("/{postId}")
    public ResponseEntity<?> postUpdate(@PathVariable("postId") Long id, @RequestBody PostUpdateReqDto postUpdateReqDto) {
        postService.updatePost(id, postUpdateReqDto);
        return ResponseEntity.status(CREATED)
                .body(success(CREATED, null));
    }

    @DeleteMapping("/{postId}")
    public ResponseEntity<?> deletePostById(@PathVariable("postId") Long id) {
        postService.deletePost(id);
        return ResponseEntity.status(CREATED)
                .body(success(CREATED, null));
    }
}
