package com.pop.models;

import java.util.Date;
import java.util.UUID;

public class Notification {
    private String username;
    private String notificationId;
    private NotificationType notificationType;
    private Date timestamp;
    private String title;
    private String primaryMediaUrl;
    private String secondaryMediaUrl;
    private NotificationTargetType targetType;
    private String targetResourceId;
    private boolean opened;
    private Date expiryTime;            // TTL (time-to-live) attribute

    private static Notification buildPostNotifications(String username, String title, NotificationType notificationType, String primaryMediaUrl, String secondaryMediaUrl, String targetResourceId) {
        Notification newNotification = new Notification(
                username,
                UUID.randomUUID().toString(),
                notificationType,
                new Date(),
                title,
                primaryMediaUrl,
                secondaryMediaUrl,
                NotificationTargetType.Post,
                targetResourceId,
                false,
                null
        );
        return newNotification;
    }

    private static Notification buildUserNotifications(String username, String title, NotificationType notificationType, String primaryMediaUrl, String secondaryMediaUrl, String targetResourceId) {
        Notification newNotification = new Notification(
                username,
                UUID.randomUUID().toString(),
                notificationType,
                new Date(),
                title,
                primaryMediaUrl,
                secondaryMediaUrl,
                NotificationTargetType.User,
                targetResourceId,
                false,
                null
        );
        return newNotification;
    }

    public static Notification buildCommentNotification(String username, String title, String primaryMediaUrl, String secondaryMediaUrl, String targetResourceId) {
        return Notification.buildPostNotifications(username, title, NotificationType.Comment, primaryMediaUrl, secondaryMediaUrl, targetResourceId);
    }

    public static Notification buildReactionNotification(String username, String title, String primaryMediaUrl, String secondaryMediaUrl, String targetResourceId) {
        return Notification.buildPostNotifications(username, title, NotificationType.Reaction, primaryMediaUrl, secondaryMediaUrl, targetResourceId);
    }


    public static Notification buildTagRequestNotification(String username, String title, String primaryMediaUrl, String secondaryMediaUrl, String targetResourceId) {
        return Notification.buildPostNotifications(username, title, NotificationType.TagRequest, primaryMediaUrl, secondaryMediaUrl, targetResourceId);
    }


    public static Notification buildTagResponseNotification(String username, String title, String primaryMediaUrl, String secondaryMediaUrl, String targetResourceId) {
        return Notification.buildPostNotifications(username, title, NotificationType.TagResponse, primaryMediaUrl, secondaryMediaUrl, targetResourceId);
    }


    public static Notification buildFollowNotification(String username, String title, String primaryMediaUrl, String secondaryMediaUrl, String targetResourceId) {
        return Notification.buildUserNotifications(username, title, NotificationType.Follow, primaryMediaUrl, secondaryMediaUrl, targetResourceId);
    }

    private Notification(String username, String notificationId, NotificationType notificationType, Date timestamp, String title, String primaryMediaUrl, String secondaryMediaUrl, NotificationTargetType targetType, String targetResourceId, boolean opened, Date expiryTime) {
        this.username = username;
        this.notificationId = notificationId;
        this.notificationType = notificationType;
        this.timestamp = timestamp;
        this.title = title;
        this.primaryMediaUrl = primaryMediaUrl;
        this.secondaryMediaUrl = secondaryMediaUrl;
        this.targetType = targetType;
        this.targetResourceId = targetResourceId;
        this.opened = opened;
        this.expiryTime = expiryTime;
    }
}
