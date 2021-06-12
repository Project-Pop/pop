package com.pop.dao;

import java.util.List;

import com.pop.models.Posts;

public interface PostsDao {
	public void createPost(Posts post);
	public Posts getPostByPostId(String postId);
	public List<Posts> getPostUploadedByUsername(String username);
	public List<Posts> getMyTaggedPostsByUsername(String username);
	public void declinePost(String postId);
	public void acceptPost(String postId);
	public void tagByUsername(String username);
	public void reactToPost(String username, String reactionString);
	public void removeFromTaggedPost(String username, String postId);
	// we have to create one more table for deleted posts
	public void deletePost(String postId); 	
	public void reportPost(String postId, String message);
}
