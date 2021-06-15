package com.pop.controller;

import com.pop.common.Response;
import com.pop.dto.*;
import com.pop.models.Comments;
import com.pop.service.CommentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/v1/posts")
public class PostController {
	
	@Autowired
	private CommentService commentService;

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
    public List<Comments> getCommentsOnPost(@PathVariable String postId, HttpServletResponse response) throws IOException {
        Response res = commentService.getCommentsOnPost(postId);
        if(res.isContainsError()) {
        	response.sendError(res.getCode(), res.getMessage());
        	return null;
        }
    	return (List<Comments>) res.getData();
    }

    @PostMapping("/{postId}/comments")
    void commentOnPost(@PathVariable String postId, @RequestBody CommentDto commentDto, HttpServletResponse response) throws IOException {
        Response res = commentService.commentOnPost(postId, commentDto);
        if(res.isContainsError()) {
        	response.sendError(res.getCode(), res.getMessage());
        }
        else response.setStatus(res.getCode());
    }

    @PatchMapping("/{postId}/comments/{commentId}")
    void editComment(@PathVariable String commentId, @RequestBody CommentDto commentDto, HttpServletResponse response) throws IOException {
    	Response res = commentService.editComment(commentId, commentDto);
        if(res.isContainsError()) {
        	response.sendError(res.getCode(), res.getMessage());
        }
        else response.setStatus(res.getCode());
    }


    @DeleteMapping("/{postId}/comments/{commentId}")
    void deleteComment(@PathVariable String postId, @PathVariable String commentId, HttpServletResponse response) throws IOException {
    	Response res = commentService.deleteComment(commentId);
        if(res.isContainsError()) {
        	response.sendError(res.getCode(), res.getMessage());
        }
        else response.setStatus(res.getCode());
    }


    @PostMapping("/{postId}/comments/{commentId}/like")
    void likeCommnent(@PathVariable String postId,@PathVariable String commentId, HttpServletResponse response) throws IOException {
    	Response res = commentService.like(commentId);
        if(res.isContainsError()) {
        	response.sendError(res.getCode(), res.getMessage());
        }
        else response.setStatus(res.getCode());;
    }
    
    @PostMapping("/{postId}/comments/{commentId}/unlike")
    void unlikeCommnent(@PathVariable String postId,@PathVariable String commentId, HttpServletResponse response) throws IOException {
    	Response res = commentService.unlike(commentId);
        if(res.isContainsError()) {
        	response.sendError(res.getCode(), res.getMessage());
        }
        else response.setStatus(res.getCode());;
    }
    
}
