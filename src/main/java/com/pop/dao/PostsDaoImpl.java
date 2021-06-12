package com.pop.dao;

import java.util.List;

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
		String sql = "INSERT INTO Posts (postId, imageUrl, username, timeStamp) values (?, ?, ?, ?)";
		jt.update(sql, post.getPostId(), post.getImageUrl(), post.getUsername(), post.getTimeStamp());
	}
	
	public List<Reactions> getReactions(String postId) {
		String sql = "SELECT reactionString, count(*) FROM UserReactions where postId = ? GROUP BY reactionString";
		return jt.query(sql, new BeanPropertyRowMapper<Reactions>(Reactions.class));
	}
	
	@Override
	public Posts getPostByPostId(String postId) {
		String sql = "Select * from Posts where postId = ?";
		Posts p = jt.queryForObject(sql, new BeanPropertyRowMapper<Posts>(Posts.class), postId);
		p.setReactions(getReactions(postId));
		return p;
	}
	
	@Override
	public List<Posts> getPostUploadedByUsername(String username) {
		String sql = "Select * from Posts where postId = ?";
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
	public void reactToPost(String username, String reactionString, String postId) {
		String sql = "INSERT INTO UserReactions (username, postId, reactionString) values (?, ?, ?)";
		jt.update(sql, username, postId, reactionString);
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

	

}
