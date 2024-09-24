package org.example.mywebservice.controller;


import lombok.RequiredArgsConstructor;
import org.example.mywebservice.dto.request.PostCreateReqDto;
import org.example.mywebservice.dto.request.PostUpdateReqDto;
import org.example.mywebservice.repository.UserRepository;
import org.example.mywebservice.service.PostService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/posts")
public class PostController {

    private final PostService postService;
    private final UserRepository userRepository;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("posts", postService.getAllPosts());
        model.addAttribute("nickName",
                userRepository.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName())
                        .get().getNickName());
        return "list";
    }

    @GetMapping("/create")
    public String createPost(Model model) {
        model.addAttribute("post", new PostCreateReqDto());
        return "create";
    }

    @PostMapping("/create")
    public String createPost(@ModelAttribute("post") PostCreateReqDto postCreateReqDto) {
        postService.addPost(postCreateReqDto);
        return "redirect:/posts";
    }

    @GetMapping("/{id}")
    public String postFind(@PathVariable Long id, Model model) {
        model.addAttribute("post", postService.findPostById(id));
        return "detail";
    }

    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable Long id, Model model) {
        model.addAttribute("post", postService.findPostById(id));
        return "edit";
    }

    @PostMapping("{id}/edit")
    public String editPost(@PathVariable Long id, @ModelAttribute("post") PostUpdateReqDto postUpdateReqDto) {
        postService.updatePost(id, postUpdateReqDto);
        return "redirect:/posts";
    }

    @PostMapping("/{id}/delete")
    public String deletePost(@PathVariable Long id) {
        postService.deletePost(id);
        return "redirect:/posts";
    }
}
