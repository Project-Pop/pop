package com.pop.models;

import java.util.Date;

import javax.validation.constraints.NotNull;

public class Posts {
	
	String postId;
	@NotNull
	String imageUrl;
	@NotNull
	String userId;
	String views;
	Date date;
	
	UserProfile user;
	
	public Posts(String postId, @NotNull String imageUrl, @NotNull String userId, String views, Date date, UserProfile user) {
		super();
		this.postId = postId;
		this.imageUrl = imageUrl;
		this.userId = userId;
		this.views = views;
		this.date = date;
		this.user = user;
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
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
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
