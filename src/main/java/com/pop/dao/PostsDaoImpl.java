package com.pop.dao;

import java.util.List;
import java.util.stream.Collectors;

import com.pop.dto.UsernameDto;
import com.pop.models.Tagged;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import com.pop.models.Posts;
import com.pop.models.Reactions;

public class PostsDaoImpl implements PostsDao{
	
	@Autowired
	private JdbcTemplate jt;
	
	@Override
	public void createPost(Posts post) {
		String sql = "INSERT INTO Posts (postId, imageUrl, username, timeStamp, description) values (?, ?, ?, ?, ?)";
		jt.update(sql, post.getPostId(), post.getImageUrl(), post.getUsername(), post.getTimeStamp(), post.getDescription());
//		tagMultipleUsers(post.getPostId(),post.getTaggedUsers().stream().map(UsernameDto::getUsername).collect(Collectors.toList()));
	}
	
	public List<Reactions> getReactions(String postId) {
		String sql = "SELECT reactionString, count(reactionString) FROM UserReactions where postId = ? GROUP BY reactionString";
		return jt.query(sql, new BeanPropertyRowMapper<Reactions>(Reactions.class));
	}
	
	@Override
	public Posts getPostByPostId(String postId,  boolean approvedStatus) {
		String sql = "Select * from Posts where postId = ?";
		Posts p = jt.queryForObject(sql, new BeanPropertyRowMapper<Posts>(Posts.class), postId);
		p.setReactions(getReactions(postId));

		p.setTaggedUsers(
				// onlyApproved: if true, only fetch tags which are approved
				(List<Tagged>)	getTaggedUser(postId, approvedStatus)
		);
		return p;
	}
	
	public List<Tagged> getTaggedUser(String postId, boolean approvedStatus) {
		String sql = "SELECT * from Tagged where postId = ? AND approvalStatus = ?";
		return jt.query(sql, new BeanPropertyRowMapper<Tagged>(Tagged.class), postId, approvedStatus);	
	}
	
	@Override
	public List<Posts> getPostUploadedByUsername(String username) {
		String sql = "Select * from Posts where username = ?";
		return jt.query(sql, new BeanPropertyRowMapper<Posts>(Posts.class), username);
	}

	@Override
	public List<Posts> getMyTaggedPostsByUsername(String username) {
		String sql = "SELECT P.postId, imageUrl, P.username, views, timeStamp FROM Posts as P INNER JOIN Tagged as T where T.username = ? AND and T.approvalStatus = True";
		return jt.query(sql, new BeanPropertyRowMapper<Posts>(Posts.class), username);
	}
	

	@Override
	public void declinePost(String postId, String username) {
		String sql = "DELETE FROM Tagged WHERE postId = ?, username = ?";
		jt.update(sql, postId, username);
	}	

	@Override
	public void acceptPost(String postId, String username) {
		String sql = "UPDATE Tagged SET approvalStatus = True where postId = ?, username = ?";
		jt.update(sql, postId, username);
	}

	@Override
	public void tagByUsername(String postId, String username) {
		String sql = "INSERT INTO Tagged (postId, username) values (?,?)";
		jt.update(sql, postId, username);
	}

	@Override
	public void tagMultipleUsers(String postId, List<String> usernames) {
		usernames.forEach(username-> tagByUsername(postId,username));
	}

	@Override
	public void reactToPost(String username, String reactionString, String postId) {
		deleteReaction(postId, username);
		String sql = "INSERT INTO UserReactions (username, postId, reactionString) values (?, ?, ?)";
		jt.update(sql, username, postId, reactionString);
	}
	
	public void deleteReaction(String postId, String username) {
		String sql = "DELETE FROM UserReactions where postId = ? AND username = ?";
		jt.update(sql, postId, username);
	}
	

	@Override
	public void removeFromTaggedPost(String username, String postId) {
		declinePost(postId, username);
	}

	@Override
	public void deletePost(String postId) {
		String sql = "DELETE FROM Posts where postId = ?";
		jt.update(sql);
	}

	@Override
	public void reportPost(String postId, String message) {
		// TODO Auto-generated method stub
	}

	@Override
	public String getOwnerOfPost(String postId) {
		String sql = "SELECT username FROM Posts where postId = ?";
		return jt.queryForObject(sql, new BeanPropertyRowMapper<>(String.class), postId);
	}

	@Override
	public void editDescription(String postId, String description) {
		String sql = "UPDATE Posts SET description = ? WHERE postId = ?";
		jt.update(sql, description, postId);
	}

	

}
