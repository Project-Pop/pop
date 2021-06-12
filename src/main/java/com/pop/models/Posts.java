package com.pop.models;

import java.util.Date;

import javax.validation.constraints.NotNull;

public class Posts {
	
	String postId;
	@NotNull
	String imageUrl;
	@NotNull
	String username;
	String views;
	Date date;
	int likeCount = 0;
	
	UserProfile user;
	
	public Posts(String postId, @NotNull String imageUrl, @NotNull String username, String views, Date date, UserProfile user, int likeCount) {
		super();
		this.postId = postId;
		this.imageUrl = imageUrl;
		this.username = username;
		this.views = views;
		this.date = date;
		this.user = user;
		this.likeCount = likeCount;
	}
	

	public int getLikeCount() {
		return likeCount;
	}


	public void setLikeCount(int likeCount) {
		this.likeCount = likeCount;
	}


	public String getPostId() {
		return postId;
	}
	public void setPostId(String postId) {
		this.postId = postId;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	public UserProfile getUser() {
		return user;
	}
	
	public void setUser(UserProfile user) {
		this.user = user;
	}

	public String getViews() {
		return views;
	}
	public void setViews(String views) {
		this.views = views;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
	
}
