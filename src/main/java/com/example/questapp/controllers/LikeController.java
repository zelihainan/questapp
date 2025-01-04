package com.example.questapp.controllers;

import com.example.questapp.entities.Like;
import com.example.questapp.requests.LikeCreateRequest;
import com.example.questapp.responses.LikeResponse;
import com.example.questapp.services.LikeService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/likes")
public class LikeController {
    private LikeService likeService;
    public LikeController(LikeService likeService) {
        this.likeService = likeService;
    }
    @GetMapping
    public List<LikeResponse> getAllLikes(@RequestParam Optional<Long> userId, @RequestParam Optional<Long> postId) {
        return likeService.getAllLikes(userId,postId);
    }
    @PostMapping
    public Like createOneLike(@RequestBody LikeCreateRequest likeCreateRequest) {
        return likeService.createOneLike(likeCreateRequest);
    }
    @GetMapping("/{likeId}")
    public Like getOneLike(@PathVariable Long likeId) {
        return likeService.getOneLikeById(likeId);
    }

    @DeleteMapping("/{likeId}")
    public void deleteOneLike(@PathVariable Long likeId) {
        likeService.deleteOneLikeById(likeId);
    }
}
