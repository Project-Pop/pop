package com.pop.models;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.validation.constraints.NotNull;

public class Posts {
	
	String postId;
	@NotNull
	String imageUrl;
	@NotNull
	String username;
	List<Reactions> reactions;  
	String views;
	Date timeStamp;
	int likeCount = 0;
	UserProfile user;
	
	public Posts(String postId, @NotNull String imageUrl, @NotNull String username, String views, Date timeStamp, UserProfile user, int likeCount) {
		super();
		this.postId = postId;
		this.imageUrl = imageUrl;
		this.username = username;
		this.views = views;
		this.timeStamp = timeStamp;
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
	public Date getTimeStamp() {
		return timeStamp;
	}
	public void setTimeStamp(Date timeStamp) {
		this.timeStamp = timeStamp;
	}
	
	public List<Reactions> getReactions() {
		return reactions;
	}

	public void setReactions(List<Reactions> reactions) {
		this.reactions = reactions;
	}
}
