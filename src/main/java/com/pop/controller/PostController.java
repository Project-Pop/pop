package com.pop.controller;

import com.pop.dto.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/posts")
public class PostController {

    @GetMapping("/")
    void getGlobalFeed() {
        return;
    }

    @PostMapping("/")
    void postPost(@RequestBody NewPostDto newPostDto) {
        return;
    }


    @GetMapping("/home")
    void getHomeFeed() {
        return;
    }


    @GetMapping("/users/{username}")
    void getUserPosts(@PathVariable String username) {
        return;
    }


    @GetMapping("/users/{username}/uploads")
    void getUserUploads(@PathVariable String username) {
        return;
    }

    @GetMapping("/{postId}")
    void getPostDetails(@PathVariable String postId) {
        return;
    }

    @PatchMapping("/{postId}")
    void editPostDetails(@PathVariable String postId, @RequestBody PatchPostDto patchPostDto) {
        return;
    }

    @DeleteMapping("/{postId}")
    void deletePost(@PathVariable String postId) {
        return;
    }

    @PostMapping("/{postId}/tags/{username}")
    void tagApproval(@PathVariable String postId, @PathVariable String username, @RequestBody TagApprovalDto tagApprovalDto) {
        return;
    }

    @DeleteMapping("/{postId}/tags/{username}")
    void removeTag(@PathVariable String postId, @PathVariable String username) {
        return;
    }

    @PostMapping("/{postId}/reactions")
    void reactOnPost(@PathVariable String postId, @RequestBody PostReactionDto postReactionDto) {
        return;
    }


    @GetMapping("/{postId}/comments")
    void getCommentsOnPost(@PathVariable String postId) {
        return;
    }

    @PostMapping("/{postId}/comments")
    void commentOnPost(@PathVariable String postId, @RequestBody CommentDto commentDto) {
        return;
    }

    @PatchMapping("/{postId}/comments/{commentId}")
    void editComment(@PathVariable String postId, @PathVariable String commentId, @RequestBody CommentDto commentDto) {
        return;
    }


    @DeleteMapping("/{postId}/comments/{commentId}")
    void deleteComment(@PathVariable String postId, @PathVariable String commentId) {
        return;
    }


    @PostMapping("/{postId}/comments/{commentId}/reactions")
    void reactOnComment(@PathVariable String postId,@PathVariable String commentId) {
        return;
    }

}
