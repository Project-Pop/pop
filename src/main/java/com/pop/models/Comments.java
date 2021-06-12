package com.pop.models;

import javax.validation.constraints.NotNull;

public class Comments {
	@NotNull
	String commentId;
	@NotNull
	String username;
	@NotNull
	String postId;
	int likeCount;
	@NotNull
	String message;
	public Comments(@NotNull String commentId, @NotNull String username, @NotNull String postId, int likeCount,
			@NotNull String message) {
		super();
		this.commentId = commentId;
		this.username = username;
		this.postId = postId;
		this.likeCount = likeCount;
		this.message = message;
	}
	public String getCommentId() {
		return commentId;
	}
	public void setCommentId(String commentId) {
		this.commentId = commentId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPostId() {
		return postId;
	}
	public void setPostId(String postId) {
		this.postId = postId;
	}
	public int getLikeCount() {
		return likeCount;
	}
	public void setLikeCount(int likeCount) {
		this.likeCount = likeCount;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	
	
}
