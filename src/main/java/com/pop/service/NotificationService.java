package com.pop.service;

import com.pop.models.Notification;
import com.pop.models.NotificationResponseType;

import java.util.List;

public interface NotificationService {
    public void buildFollowNotification(String remoteUsername);

    public void buildPostReactionNotification(String remoteUsername, String postId, String postImageUrl);

    public void buildCommentNotification(String remoteUsername, String comment, String postId, String postImageUrl);

    public void buildCommentReactionNotification(String remoteUsername,String comment, String postId, String postImageUrl);

    public void buildTagRequestNotification(String remoteUsername, String postId, String postImageUrl);

    public void buildTagResponseNotification(String remoteUsername, NotificationResponseType responseType, String postId, String postImageUrl);

    public void setNotificationStatusToOpened(String notificationId);

    public void deleteNotification(String notificationId);

    public List<Notification> getNotifications();

}
