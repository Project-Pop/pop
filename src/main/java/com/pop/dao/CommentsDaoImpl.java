package com.pop.dao;

import com.pop.models.Comments;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class CommentsDaoImpl implements CommentsDao {

    @Autowired
    JdbcTemplate jt;

    @Override
    public void addComment(Comments comment) {
        String sql = "INSERT INTO Comments (commentId, postId, username, message) values (?,?,?,?)";
        jt.update(sql, comment.getCommentId(), comment.getPostId(), comment.getUsername(), comment.getMessage());
    }

    @Override
    public void editComment(String commentId, String message) {
        String sql = "UPDATE Comments SET message = ? where commentId = ?";
        jt.update(sql, message, commentId);
    }

    @Override
    public void deleteComment(String commentId) {
        String sql = "DELETE FROM Comments WHERE commentId = ?";
        jt.update(sql, commentId);

    }

    @Override
    public List<Comments> getCommentsByPost(String postId) {
        String sql = "SELECT * FROM Comments where postId = ?";
        return jt.query(sql, new BeanPropertyRowMapper<>(Comments.class), postId);
    }

    @Override
    public void like(String commentId, String username) {
        String sql = "INSERT INTO CommentsReactionCounter values (?, ?)";
        jt.update(sql, commentId, username);
        sql = "UPDATE Comments SET likeCount= likeCount + 1 WHERE commentId = ?";
        jt.update(sql, commentId);
    }

    @Override
    public void unlike(String commentId, String username) {
        String sql = "DELETE FROM CommentsReactionCounter where commentId = ? AND username = ?";
        jt.update(sql, commentId, username);
        sql = "UPDATE Comments SET likeCount= likeCount - 1 WHERE commentId = ?";
        jt.update(sql, commentId);
    }

    @Override
    public String getCommentOwner(String commentId) {
        String sql = "SELECT username FROM Comments WHERE commentId = ?";
        return jt.queryForObject(sql, new BeanPropertyRowMapper<>(String.class), commentId);
    }

    @Override
    public Comments getCommentByCommentId(String commentId) {
        String sql = "SELECT * FROM Comments WHERE commentId = ?";
        return jt.queryForObject(sql, new BeanPropertyRowMapper<>(Comments.class), commentId);
    }


}
