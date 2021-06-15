package com.pop.dao;

import java.util.List;

import com.pop.models.Posts;

public interface PostsDao {
	public void createPost(Posts post);
	// We have to add logic to get all tagged in this query only using join. Will add that when we will have some data
	// Similarly adding reaction Count logic 
	public Posts getPostByPostId(String postId,  boolean approvedStatus);
	public List<Posts> getPostUploadedByUsername(String username);
	public List<Posts> getMyTaggedPostsByUsername(String username);
	public void declinePost(String postId, String username);
	public void acceptPost(String postId, String username);

	public void tagByUsername(String postId, String username);
	public void tagMultipleUsers(String postId, List<String> usernames);


	public void reactToPost(String username, String reactionString,  String postId);
	public void removeFromTaggedPost(String username, String postId);
	// we have to create one more table for deleted posts
	public void deletePost(String postId); 	
	public void reportPost(String postId, String message);
	public String  getOwnerOfPost(String postId);
	public void editDescription(String postId,String description);
}
