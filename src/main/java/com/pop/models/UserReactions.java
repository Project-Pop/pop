package com.pop.models;

import javax.validation.constraints.NotNull;

public class UserReactions {
	@NotNull
	String username;
	@NotNull
	String postId;
	@NotNull
	String reactionString;
	UserProfile user;
	
	public UserReactions(@NotNull String username, @NotNull String postId, @NotNull String reactionString,
			UserProfile user) {
		super();
		this.username = username;
		this.postId = postId;
		this.reactionString = reactionString;
		this.user = user;
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
	public String getReactionString() {
		return reactionString;
	}
	public void setReactionString(String reactionString) {
		this.reactionString = reactionString;
	}
	public UserProfile getUser() {
		return user;
	}
	public void setUser(UserProfile user) {
		this.user = user;
	}
	
}
