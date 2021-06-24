package com.pop.service.AwsClientService;

import com.pop.dto.UsernameDto;
import com.pop.models.Notification;

import java.util.Date;
import java.util.List;

public interface SqsService {
    public void sendNotificationMessage(Notification notification);

    public void sendNotificationMessage(Notification notification, List<UsernameDto> taggedUsers);

    public void sendNotificationMessage(Notification notification, String postId, String owner);

    public void sendPostForFeedGeneration(String username, String postId, String imageUrl, Date timestamp);
}
