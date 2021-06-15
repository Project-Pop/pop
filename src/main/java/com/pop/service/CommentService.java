package com.pop.service;

import com.pop.common.Response;
import com.pop.dto.CommentDto;

public interface CommentService {

    public Response getCommentsOnPost(String postId);

    public Response commentOnPost(String postId, CommentDto commentDto);

    public Response editComment(String postId, String commentId, CommentDto commentDto);

    public Response deleteComment(String postId, String commentId);

    public Response reactOnComment(String postId, String commentId);

}
