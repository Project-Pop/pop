package com.pop.controller;

import com.pop.dto.*;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/v1/posts")
public class PostController {

    @GetMapping("/")
    void getGlobalFeed( HttpServletResponse response) {
        return;
    }

    @PostMapping("/")
    void postPost(@RequestBody NewPostDto newPostDto, HttpServletResponse response) {
        return;
    }


    @GetMapping("/home")
    void getHomeFeed(HttpServletResponse response) {
        return;
    }


    @GetMapping("/users/{username}")
    void getUserPosts(@PathVariable String username, HttpServletResponse response) {
        return;
    }


    @GetMapping("/users/{username}/uploads")
    void getUserUploads(@PathVariable String username, HttpServletResponse response) {
        return;
    }

    @GetMapping("/{postId}")
    void getPostDetails(@PathVariable String postId, HttpServletResponse response) {
        return;
    }

    @PatchMapping("/{postId}")
    void editPostDetails(@PathVariable String postId, @RequestBody PatchPostDto patchPostDto, HttpServletResponse response) {
        return;
    }

    @DeleteMapping("/{postId}")
    void deletePost(@PathVariable String postId, HttpServletResponse response) {
        return;
    }

    @PostMapping("/{postId}/tags/{username}")
    void tagApproval(@PathVariable String postId, @PathVariable String username, @RequestBody TagApprovalDto tagApprovalDto, HttpServletResponse response) {
        return;
    }

    @DeleteMapping("/{postId}/tags/{username}")
    void removeTag(@PathVariable String postId, @PathVariable String username, HttpServletResponse response) {
        return;
    }

    @PostMapping("/{postId}/reactions")
    void reactOnPost(@PathVariable String postId, @RequestBody PostReactionDto postReactionDto, HttpServletResponse response) {
        return;
    }


    @GetMapping("/{postId}/comments")
    void getCommentsOnPost(@PathVariable String postId, HttpServletResponse response) {
        return;
    }

    @PostMapping("/{postId}/comments")
    void commentOnPost(@PathVariable String postId, @RequestBody CommentDto commentDto, HttpServletResponse response) {
        return;
    }

    @PatchMapping("/{postId}/comments/{commentId}")
    void editComment(@PathVariable String postId, @PathVariable String commentId, @RequestBody CommentDto commentDto, HttpServletResponse response) {
        return;
    }


    @DeleteMapping("/{postId}/comments/{commentId}")
    void deleteComment(@PathVariable String postId, @PathVariable String commentId, HttpServletResponse response) {
        return;
    }


    @PostMapping("/{postId}/comments/{commentId}/reactions")
    void reactOnComment(@PathVariable String postId,@PathVariable String commentId, HttpServletResponse response) {
        return;
    }

}
