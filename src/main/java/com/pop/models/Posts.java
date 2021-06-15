package com.pop.models;

import com.pop.dto.UsernameDto;

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


	String description;

	List<Reactions> reactions;

	List<Tagged> taggedUsers;

	int views;
	Date timeStamp;
	int likeCount = 0;

	public Posts(String postId, @NotNull String imageUrl, @NotNull String username, String description, int views, Date timeStamp, int likeCount) {
		super();
		this.postId = postId;
		this.imageUrl = imageUrl;
		this.username = username;
		this.description  = description;
		this.views = views;
		this.timeStamp = timeStamp;
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


	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getViews() {
		return views;
	}
	public void setViews(int views) {
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

	public List<Tagged> getTaggedUsers() {
		return taggedUsers;
	}

	public void setTaggedUsers(List<Tagged> taggedUsers) {
		this.taggedUsers = taggedUsers;
	}
}
