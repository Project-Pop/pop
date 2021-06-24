package com.pop.service;

import com.pop.common.Response;
import com.pop.dao.CommentsDao;
import com.pop.dao.PostsDao;
import com.pop.dto.CommentDto;
import com.pop.models.Comments;
import com.pop.models.JwtUser;
import com.pop.utils.MediaFilenameBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.UUID;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentsDao commentsDao;

    @Autowired
    private PostsDao postsDao;

    @Autowired
    private NotificationService notificationService;

    @Autowired
    private StorageService storageService;

    public boolean amITheOwnerOfThisComment(String commentId) {
        var principalUser = (JwtUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = principalUser.getUsername();
        return (username == commentsDao.getCommentOwner(commentId));
    }

    @Override
    public Response getCommentsOnPost(String postId) {
        List<Comments> comments;
        try {
            comments = commentsDao.getCommentsByPost(postId);
        } catch (Exception e) {
            System.out.println(e.getCause());
            return Response.error(e.getCause().getMessage(), HttpServletResponse.SC_BAD_REQUEST);
        }
        return new Response(comments, "CommentsFetched", HttpServletResponse.SC_OK);
    }

    @Override
    public Response commentOnPost(String postId, CommentDto commentDto) {
        var principalUser = (JwtUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = principalUser.getUsername();
        String commentId = UUID.randomUUID().toString();
        Comments comment = new Comments(commentId, username, postId, 0, commentDto.getMessage());
        try {
            commentsDao.addComment(comment);
            var postOwner = postsDao.getOwnerOfPost(postId);
            // sending notification
            notificationService.buildCommentNotification(
                    postOwner,
                    commentDto.getMessage(),
                    postId,
                    storageService.getMediaUrlFromFilename(MediaFilenameBuilder.buildPostMediaFilename(postId)),
                    postOwner);

        } catch (Exception e) {
            return Response.error(e.getCause().getMessage(), HttpServletResponse.SC_BAD_REQUEST);
        }
        return Response.ok("Comment Added", HttpServletResponse.SC_OK);
    }

    @Override
    public Response editComment(String commentId, CommentDto commentDto) {

        if (amITheOwnerOfThisComment(commentId) == false) {
            return Response.error("You cannot edit this", HttpServletResponse.SC_FORBIDDEN);
        }
        try {
            commentsDao.editComment(commentId, commentDto.getMessage());
        } catch (Exception e) {
            return Response.error(e.getCause().getMessage(), HttpServletResponse.SC_BAD_REQUEST);
        }
        return Response.ok("Comment Edited", HttpServletResponse.SC_OK);
    }

    @Override
    public Response deleteComment(String commentId) {
        if (amITheOwnerOfThisComment(commentId) == false) {
            return Response.error("You cannot edit this", HttpServletResponse.SC_FORBIDDEN);
        }
        try {
            commentsDao.deleteComment(commentId);
        } catch (Exception e) {
            return Response.error(e.getCause().getMessage(), HttpServletResponse.SC_BAD_REQUEST);
        }
        return Response.ok("Comment Deleted", HttpServletResponse.SC_OK);
    }

    @Override
    public Response like(String commentId) {
        var principalUser = (JwtUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = principalUser.getUsername();
        try {
            commentsDao.like(commentId, username);

            var commentData = commentsDao.getCommentByCommentId(commentId);

            // sending notification
            notificationService.buildCommentReactionNotification(
                    commentData.getUsername(),
                    commentData.getMessage(),
                    commentData.getPostId(),
                    storageService.getMediaUrlFromFilename(MediaFilenameBuilder.buildPostMediaFilename(commentData.getPostId()))
            );

        } catch (Exception e) {
            return Response.error(e.getCause().getMessage(), HttpServletResponse.SC_BAD_REQUEST);
        }
        return Response.ok("Comment Liked", HttpServletResponse.SC_OK);
    }

    @Override
    public Response unlike(String commentId) {
        var principalUser = (JwtUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = principalUser.getUsername();
        try {
            commentsDao.unlike(commentId, username);

        } catch (Exception e) {
            return Response.error(e.getCause().getMessage(), HttpServletResponse.SC_BAD_REQUEST);
        }
        return Response.ok("Comment Unliked", HttpServletResponse.SC_OK);
    }

}
