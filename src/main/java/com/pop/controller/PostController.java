package com.pop.controller;

import com.pop.common.Response;
import com.pop.dto.*;
import com.pop.models.Comments;
import com.pop.models.Posts;
import com.pop.service.CommentService;
import com.pop.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/v1/posts")
public class PostController {

    @Autowired
    private CommentService commentService;

    @Autowired
    private PostService postService;

    @GetMapping("/")
    void getGlobalFeed(HttpServletResponse response) throws IOException {
        return;
    }

    @PostMapping("/")
    public NewPostDto postPost(@RequestBody NewPostDto newPostDto, MultipartFile image, MultipartFile miniImage, HttpServletResponse response) throws IOException {
    	
        Response res = postService.createPost(newPostDto, image, miniImage);
        if (res.isContainsError()) {
            response.sendError(res.getCode(), res.getError());
        } else {
            response.setStatus(res.getCode());
        }

        return (NewPostDto) res.getData();
    }


    @GetMapping("/home")
    void getHomeFeed(HttpServletResponse response) throws IOException {
        return;
    }


    @GetMapping("/users/{username}")
    List<Posts> getUserPosts(@PathVariable String username, HttpServletResponse response) throws IOException {
        Response res = postService.getUserPosts(username);
        if (res.isContainsError()) {
            response.sendError(res.getCode(), res.getError());
        } else {
            response.setStatus(res.getCode());
        }
        return (List<Posts>) res.getData();
    }


    @GetMapping("/users/{username}/uploads")
    List<Posts> getUserUploads(@PathVariable String username, HttpServletResponse response) throws IOException {
        Response res = postService.getUserUploads(username);
        if (res.isContainsError()) {
            response.sendError(res.getCode(), res.getError());
        } else {
            response.setStatus(res.getCode());
        }

        return (List<Posts>) res.getData();
    }

    @GetMapping("/{postId}")
    Posts getPostDetails(@PathVariable String postId, HttpServletResponse response) throws IOException {
        Response res = postService.getPostDetails(postId);
        if (res.isContainsError()) {
            response.sendError(res.getCode(), res.getError());
        } else {
            response.setStatus(res.getCode());
        }

        return (Posts) res.getData();
    }

    @PatchMapping("/{postId}")
    String editPostDetails(@PathVariable String postId, @RequestBody PatchPostDto patchPostDto, HttpServletResponse response) throws IOException {
        Response res = postService.editPostDetails(postId, patchPostDto);
        if (res.isContainsError()) {
            response.sendError(res.getCode(), res.getError());
        } else {
            response.setStatus(res.getCode());
        }

        return res.getMessage();
    }

    @DeleteMapping("/{postId}")
    String deletePost(@PathVariable String postId, HttpServletResponse response) throws IOException {
        Response res = postService.deletePost(postId);
        if (res.isContainsError()) {
            response.sendError(res.getCode(), res.getError());
        } else {
            response.setStatus(res.getCode());
        }

        return res.getMessage();
    }

    @PostMapping("/{postId}/tags/{username}")
    String tagApproval(@PathVariable String postId, @PathVariable String username, @RequestBody TagApprovalDto tagApprovalDto, HttpServletResponse response) throws IOException {
        Response res = postService.tagApproval(postId, username, tagApprovalDto);
        if (res.isContainsError()) {
            response.sendError(res.getCode(), res.getError());
        } else {
            response.setStatus(res.getCode());
        }
        return res.getMessage();
    }

    @DeleteMapping("/{postId}/tags/{username}")
    String removeTag(@PathVariable String postId, @PathVariable String username, HttpServletResponse response) throws IOException {
        Response res = postService.removeTag(postId, username);
        if (res.isContainsError()) {
            response.sendError(res.getCode(), res.getError());
        } else {
            response.setStatus(res.getCode());
        }
        return res.getMessage();
    }

    @PostMapping("/{postId}/reactions")
    String reactOnPost(@PathVariable String postId, @RequestBody PostReactionDto postReactionDto, HttpServletResponse response) throws IOException {
        Response res = postService.reactOnPost(postId, postReactionDto);
        if (res.isContainsError()) {
            response.sendError(res.getCode(), res.getError());
        } else {
            response.setStatus(res.getCode());
        }
        return res.getMessage();
    }


    @GetMapping("/{postId}/comments")
    public List<Comments> getCommentsOnPost(@PathVariable String postId, HttpServletResponse response) throws IOException {
        Response res = commentService.getCommentsOnPost(postId);
        if (res.isContainsError()) {
            response.sendError(res.getCode(), res.getError());
        } else {
            response.setStatus(res.getCode());
        }
        return (List<Comments>) res.getData();
    }

    @PostMapping("/{postId}/comments")
    String commentOnPost(@PathVariable String postId, @RequestBody CommentDto commentDto, HttpServletResponse response) throws IOException {
        Response res = commentService.commentOnPost(postId, commentDto);
        if (res.isContainsError()) {
            response.sendError(res.getCode(), res.getError());
        } else {
            response.setStatus(res.getCode());
        }

        return res.getMessage();
    }

    @PatchMapping("/{postId}/comments/{commentId}")
    String editComment(@PathVariable String commentId, @RequestBody CommentDto commentDto, HttpServletResponse response) throws IOException {
        Response res = commentService.editComment(commentId, commentDto);
        if (res.isContainsError()) {
            response.sendError(res.getCode(), res.getError());
        } else {
            response.setStatus(res.getCode());
        }

        return res.getMessage();
    }


    @DeleteMapping("/{postId}/comments/{commentId}")
    String deleteComment(@PathVariable String postId, @PathVariable String commentId, HttpServletResponse response) throws IOException {
        Response res = commentService.deleteComment(commentId);
        if (res.isContainsError()) {
            response.sendError(res.getCode(), res.getError());
        } else {
            response.setStatus(res.getCode());
        }

        return res.getMessage();
    }


    @PostMapping("/{postId}/comments/{commentId}/like")
    String likeComment(@PathVariable String postId, @PathVariable String commentId, HttpServletResponse response) throws IOException {
        Response res = commentService.like(commentId);
        if (res.isContainsError()) {
            response.sendError(res.getCode(), res.getError());
        } else {
            response.setStatus(res.getCode());
        }

        return res.getMessage();
    }

    @PostMapping("/{postId}/comments/{commentId}/unlike")
    String unlikeComment(@PathVariable String postId, @PathVariable String commentId, HttpServletResponse response) throws IOException {
        Response res = commentService.unlike(commentId);
        if (res.isContainsError()) {
            response.sendError(res.getCode(), res.getError());
        } else {
            response.setStatus(res.getCode());
        }

        return res.getMessage();
    }

}
