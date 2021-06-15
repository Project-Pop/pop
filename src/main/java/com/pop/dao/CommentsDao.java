package com.pop.dao;

import java.util.List;

import com.pop.models.Comments;

public interface CommentsDao {
	public void addComment(Comments comment);
	public void editComment(String commentId, String message);
	public void deleteComment(String commentId);
	public List<Comments> getCommentsByPost(String postId);
	public void like(String commentId, String username);
	public void unlike(String commentId, String username);

	public void removeReaction(String commentId, String username);
	public String getCommentOwner(String commentId);
}
