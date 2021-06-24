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

    public static Notification buildPostReactionNotification(String username, String title, String primaryMediaUrl, String secondaryMediaUrl, String targetResourceId) {
        return Notification.buildPostNotifications(username, title, NotificationType.PostReaction, primaryMediaUrl, secondaryMediaUrl, targetResourceId);
    }


	public static Notification buildCommentReactionNotification(String username, String title, String primaryMediaUrl, String secondaryMediaUrl, String targetResourceId) {
		return Notification.buildPostNotifications(username, title, NotificationType.CommentReaction, primaryMediaUrl, secondaryMediaUrl, targetResourceId);
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

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getNotificationId() {
		return notificationId;
	}

	public void setNotificationId(String notificationId) {
		this.notificationId = notificationId;
	}

	public NotificationType getNotificationType() {
		return notificationType;
	}

	public void setNotificationType(NotificationType notificationType) {
		this.notificationType = notificationType;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPrimaryMediaUrl() {
		return primaryMediaUrl;
	}

	public void setPrimaryMediaUrl(String primaryMediaUrl) {
		this.primaryMediaUrl = primaryMediaUrl;
	}

	public String getSecondaryMediaUrl() {
		return secondaryMediaUrl;
	}

	public void setSecondaryMediaUrl(String secondaryMediaUrl) {
		this.secondaryMediaUrl = secondaryMediaUrl;
	}

	public NotificationTargetType getTargetType() {
		return targetType;
	}

	public void setTargetType(NotificationTargetType targetType) {
		this.targetType = targetType;
	}

	public String getTargetResourceId() {
		return targetResourceId;
	}

	public void setTargetResourceId(String targetResourceId) {
		this.targetResourceId = targetResourceId;
	}

	public boolean isOpened() {
		return opened;
	}

	public void setOpened(boolean opened) {
		this.opened = opened;
	}

	public Date getExpiryTime() {
		return expiryTime;
	}

	public void setExpiryTime(Date expiryTime) {
		this.expiryTime = expiryTime;
	}
    
}
