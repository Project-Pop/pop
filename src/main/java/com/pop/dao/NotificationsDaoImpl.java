package com.pop.dao;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import com.pop.models.Notification;

public class NotificationsDaoImpl implements NotificationDao{
	
	@Autowired
	private JdbcTemplate jt;
	
	@Override
	public void saveNotification(Notification n) {
		String sql = "INSERT INTO Notifications (notificationId, username, notificationType, timestamp, title, primaryMediaUrl, secondaryMediaUrl, targetType, targetResourceId, opened, expirtyDate) values (?,?,?,?,?,?,?,?,?,?,?)";
		jt.update(sql, n.getNotificationId(), n.getUsername(),n.getNotificationType(), n.getTimestamp(), n.getTitle(), n.getPrimaryMediaUrl(), n.getSecondaryMediaUrl(), n.getTargetType(), n.getTargetResourceId(), n.isOpened(), n.getExpiryTime());
	}

	@Override
	public void markNotificationAOpened(String notificationId) {
		String sql = "UPDATE Notifications SET opened = TRUE where notificationId = ?";
		jt.update(sql, notificationId);
	}

	@Override
	public void deleteNotification(String notificationId) {
		String sql = "DELETE FROM Notifications where notificationId = ?";
		jt.update(sql, notificationId);
	}

	@Override
	public List<Notification> getNotifications(String username) {
		String sql = "SELECT * FROM Notifications where username = ? ORDER BY timestamp DESC LIMIT 15";
		return jt.query(sql, new BeanPropertyRowMapper<>(Notification.class), username);
		
	}

	@Override
	public List<Notification> getMoreNotification(String username, Date timestamp) {
		String sql = "SELECT * FROM Notifications where username = ? AND timestamp > ? ORDER BY timestamp DESC LIMIT 15";
		return jt.query(sql, new BeanPropertyRowMapper<>(Notification.class), username, timestamp);		
	}

}
