package com.example.questapp.services;

import com.example.questapp.entities.Post;
import com.example.questapp.entities.User;
import com.example.questapp.repos.PostRepository;
import com.example.questapp.requests.PostCreateRequest;
import com.example.questapp.requests.PostUpdateRequest;
import com.example.questapp.responses.LikeResponse;
import com.example.questapp.responses.PostResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PostService {
    private PostRepository postRepository;
    private LikeService likeService;
    private UserService userService;

    public PostService(PostRepository postRepository, UserService userService) {
        this.postRepository = postRepository;
        this.userService = userService;
    }
    @Autowired
    public void setLikeService(LikeService likeService) {
        this.likeService = likeService;
    }

    public List<PostResponse> getAllPosts(Optional<Long> userId) {
        List<Post> list;
        if (userId.isPresent()) {
             list = postRepository.findByUserId(userId.get());
        }
        else {
            list = postRepository.findAll();
        }
        return list.stream().map(p -> {
            List<LikeResponse> likes = likeService.getAllLikes(Optional.ofNullable(null), Optional.of(p.getId()));
         return new PostResponse(p, likes);}).collect(Collectors.toList());
    }

    public Post getOnePostById(Long postId) {
        return postRepository.findById(postId).orElse(null);
    }

    public Post createOnePost(PostCreateRequest newPostRequest) {
        User user = userService.getOneUserById(newPostRequest.getUserId());
        if (user == null) {
            return null;
        }
        Post toSave = new Post();
        toSave.setId(newPostRequest.getId());
        toSave.setText(newPostRequest.getText());
        toSave.setTitle(newPostRequest.getTitle());
        toSave.setUser(user);
        toSave.setCreateDate(new Date());
        return postRepository.save(toSave);
    }

    public Post updateOnePostById(Long postId, PostUpdateRequest updatePost) {
        Optional<Post> post = postRepository.findById(postId);
        if (post.isPresent()) {
            Post postToUpdate = post.get();
            postToUpdate.setText(updatePost.getText());
            postToUpdate.setTitle(updatePost.getTitle());
            postRepository.save(postToUpdate);
            return postToUpdate;
        }
        return null;
    }

    public void deleteOnePostById(Long postId) {
        postRepository.deleteById(postId);
    }
}
