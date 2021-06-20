package com.pop.service;

import com.pop.models.Notification;
import com.pop.models.NotificationResponseType;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface NotificationService {
    public void buildFollowNotification(String remoteUsername);

    public void buildReactionNotification(String remoteUsername, String postId, String postImageUrl);

    public void buildCommentNotification(String remoteUsername, String comment, String postId, String postImageUrl);

    public void buildTagRequestNotification(String remoteUsername, String postId, String postImageUrl);

    public void buildTagResponseNotification(String remoteUsername, NotificationResponseType responseType, String postId, String postImageUrl);

    public void setNotificationStatusToOpened(String notificationId);

    public void deleteNotification(String notificationId);

    public List<Notification> getNotifications();

}
