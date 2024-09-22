package com.example.mywebservice.controller;

import com.example.mywebservice.dto.PostDto;
import com.example.mywebservice.service.PostService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/posts")
public class PostApiController {

    private final PostService postService;

    public PostApiController(PostService postService) {
        this.postService = postService;
    }

    // 전체 게시글 목록 조회
    // http://localhost:8080/api/posts
    @GetMapping
    public List<PostDto> getAllPosts() {
        return postService.getAllPosts();
    }

    // 게시글 상세 보기
    @GetMapping("/{id}")
    public Optional<PostDto> detail(@PathVariable Long id) {
        return postService.getPostById(id);
    }

    // 새로운 게시글 생성
    @PostMapping
    @ResponseBody
    public void createPost(PostDto postDto) {
        postService.createPost(postDto);
    }

    // 게시글 수정
    @PutMapping("/{id}")
    @ResponseBody
    public void editForm(@PathVariable Long id, PostDto postDto) {
        postService.updatePost(id, postDto);
    }

    // 게시글 삭제
    @DeleteMapping("/{id}")
    public void deletePost(@PathVariable Long id) {
        postService.deletePost(id);
    }
}
