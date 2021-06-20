package com.pop.service;

import com.pop.models.JwtUser;
import com.pop.models.Notification;
import com.pop.models.NotificationResponseType;
import com.pop.utils.MediaUrlBuilder;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationServiceImpl implements NotificationService {

    @Override
    public void buildFollowNotification(String remoteUsername) {
        String myUsername = ((JwtUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
        String title = myUsername + " started following you";
        var newNotification = Notification.buildFollowNotification(
                remoteUsername,
                title,
                MediaUrlBuilder.buildUserStaticImageUrl(myUsername),
                null,
                myUsername);
        //        TODO: save notification
        //        TODO: send notification via aws sns
    }

    @Override
    public void buildPostReactionNotification(String remoteUsername, String postId, String postImageUrl) {
        String myUsername = ((JwtUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
        String title = myUsername + " reacted on your post";
        var newNotification = Notification.buildReactionNotification(
                remoteUsername,
                title,
                MediaUrlBuilder.buildUserStaticImageUrl(myUsername),
                postImageUrl,
                postId);
        //        TODO: save notification
        //        TODO: send notification via aws sns

    }

    @Override
    public void buildCommentNotification(String remoteUsername, String comment, String postId, String postImageUrl) {
        String myUsername = ((JwtUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
        String title = myUsername + " commented: " + comment;

        var newNotification = Notification.buildCommentNotification(
                remoteUsername,
                title,
                MediaUrlBuilder.buildUserStaticImageUrl(myUsername),
                postImageUrl,
                postId
        );
        //        TODO: save notification
        //        TODO: send notification via aws sns

    }

    @Override
    public void buildCommentReactionNotification(String remoteUsername, String comment, String postId, String postImageUrl) {
        String myUsername = ((JwtUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
        String title = myUsername + " liked your comment: " + comment;
        var newNotification = Notification.buildReactionNotification(
                remoteUsername,
                title,
                MediaUrlBuilder.buildUserStaticImageUrl(myUsername),
                postImageUrl,
                postId);
        //        TODO: save notification
        //        TODO: send notification via aws sns
    }

    @Override
    public void buildTagRequestNotification(String remoteUsername, String postId, String postImageUrl) {
        String myUsername = ((JwtUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
        String title = myUsername + " tagged you. Accept the tag if you like it.";
        var newNotification = Notification.buildTagRequestNotification(
                remoteUsername,
                title,
                MediaUrlBuilder.buildUserStaticImageUrl(myUsername),
                postImageUrl,
                postId);
        //        TODO: save notification
        //        TODO: send notification via aws sns
    }

    @Override
    public void buildTagResponseNotification(String remoteUsername, NotificationResponseType responseType, String postId, String postImageUrl) {
        String myUsername = ((JwtUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
        String title = myUsername;
        if (responseType == NotificationResponseType.accept) {
            title += " accepted your tag request.";
        } else {
            title += " denied your tag request.";
        }

        var newNotification = Notification.buildTagResponseNotification(
                remoteUsername,
                title,
                MediaUrlBuilder.buildUserStaticImageUrl(myUsername),
                postImageUrl,
                postId);

        //        TODO: save notification
        //        TODO: send notification via aws sns

    }

    @Override
    public void setNotificationStatusToOpened(String notificationId) {
//        TODO: update => notification.opened = true
    }

    @Override
    public void deleteNotification(String notificationId) {
//        TODO: Delete Notification
    }

    @Override
    public List<Notification> getNotifications() {
//        TODO: fetch notifications from database
        return null;
    }
}
