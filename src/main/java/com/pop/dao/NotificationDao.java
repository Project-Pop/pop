package com.pop.dao;

import java.util.Date;
import java.util.List;

import com.pop.models.Notification;

public interface NotificationDao {
	public void saveNotification(Notification notification);
	public void markNotificationAOpened(String notificationId);
	public void deleteNotification(String notificationId);
	public List<Notification> getNotifications(String username);
	public List<Notification> getMoreNotification(String username, Date timestamp);
}
