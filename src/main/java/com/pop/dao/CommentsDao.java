package com.pop.dao;

import java.util.List;

import com.pop.models.Comments;

public interface CommentsDao {
	public void addComment(Comments comment);
	public void deleteComment(String commentId);
	public List<Comments> getCommentsByPost(String postId);
	public void reactToComment(String commentId, String reactionString);
	public void removeReaction(String commentId);
}
