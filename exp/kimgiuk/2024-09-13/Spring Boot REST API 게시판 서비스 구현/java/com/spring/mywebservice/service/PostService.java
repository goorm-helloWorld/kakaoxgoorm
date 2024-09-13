package com.spring.mywebservice.service;

import com.spring.mywebservice.dto.PostDto;
import com.spring.mywebservice.entity.Post;
import com.spring.mywebservice.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PostService {
    @Autowired
    private PostRepository postRepository;

    // 게시글 작성
    public PostDto addPost(PostDto postDto) {
        Post post = new Post(postDto.getTitle(), postDto.getContent(), postDto.getAuthor());
        Post savedPost = postRepository.save(post);
        return convertPostToPostDto(savedPost);
    }

    // 게시글 목록 보기
    public List<PostDto> getAllPosts() {
        return postRepository.findAll()
                .stream()
                .map(this::convertPostToPostDto)
                .collect(Collectors.toList());
    }

    // 게시글 상세보기
    public Optional<PostDto> getPostById(Long id) {
        return postRepository.findById(id).map(this::convertPostToPostDto);
    }

    // 게시글 수정
    public PostDto updatePost(Long id, PostDto postDto) {
        return postRepository.findById(id)
                .map(post -> {
                    post.setTitle(postDto.getTitle());
                    post.setContent(postDto.getContent());
                    post.setAuthor(postDto.getAuthor());
                    return convertPostToPostDto(postRepository.save(post));
                })
                .orElseThrow(() -> new RuntimeException("Post not found"));
    }

    // 게시글 삭제
    public void deletePost(Long id) {
        postRepository.deleteById(id);
    }

    // Post -> PostDto
    private PostDto convertPostToPostDto(Post post) {
        PostDto postDto = new PostDto();
        postDto.setId(post.getId());
        postDto.setTitle(post.getTitle());
        postDto.setContent(post.getContent());
        postDto.setAuthor(post.getAuthor());
        return postDto;
    }
}
