package com.pop.service;

import com.pop.dto.*;

public interface PostService {

    public void getGlobalFeed();

    public void createPost(NewPostDto newPostDto);

    public void getHomeFeed();

    public void getUserPosts(String username);

    public void getUserUploads(String username);

    public void getPostDetails(String postId);

    public void editPostDetails(String postId, PatchPostDto patchPostDto);

    public void deletePost(String postId);

    public void tagApproval(String postId, String username, TagApprovalDto tagApprovalDto);

    public void removeTag(String postId, String username);

    public void reactOnPost(String postId, PostReactionDto postReactionDto);

    public void getCommentsOnPost(String postId);

    public void commentOnPost(String postId, CommentDto commentDto);

    public void editComment(String postId, String commentId, CommentDto commentDto);

    public void deleteComment(String postId, String commentId);

    public void reactOnComment(String postId, String commentId);


}
