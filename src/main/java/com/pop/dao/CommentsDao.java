package com.pop.dao;

import com.pop.models.Comments;

import java.util.List;

public interface CommentsDao {
    public void addComment(Comments comment);

    public void editComment(String commentId, String message);

    public void deleteComment(String commentId);

    public List<Comments> getCommentsByPost(String postId);

    public void like(String commentId, String username);

    public void unlike(String commentId, String username);

    public String getCommentOwner(String commentId);

    public Comments getCommentByCommentId(String commentId);
}
