package com.pop.service;

import com.pop.common.Response;
import com.pop.dto.CommentDto;

public interface CommentService {

    public Response getCommentsOnPost(String postId);

    public Response commentOnPost(String postId, CommentDto commentDto);

    public Response editComment(String commentId, CommentDto commentDto);

    public Response deleteComment(String commentId);

    public Response like(String commentId);

    public Response unlike(String commentId);

}
