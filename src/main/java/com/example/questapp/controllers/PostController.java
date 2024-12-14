package com.example.questapp.controllers;

import com.example.questapp.entities.Post;
import com.example.questapp.services.PostService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/posts")
public class PostController {
    private PostService postService;
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping
    public List<Post> getAllPosts(@RequestParam Optional<Long> userId) {
         return postService.getAllPosts(userId);
    }

    @PostMapping
    public Post createOnePost(@RequestBody Post newPost) {
        return postService.createOnePost(newPost);
    }

    @GetMapping("/{postId}")
    public Post getOnePost(@PathVariable Long postId) {
        return postService.getOnePostById(postId);
    }
}
