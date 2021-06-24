package com.pop.service.NotificationService;

import com.pop.dto.UsernameDto;
import com.pop.models.JwtUser;
import com.pop.models.Notification;
import com.pop.models.NotificationResponseType;
import com.pop.service.AwsClientService.SqsService;
import com.pop.service.AwsClientService.StorageService;
import com.pop.service.NotificationService.NotificationService;
import com.pop.utils.MediaFilenameBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationServiceImpl implements NotificationService {

    @Autowired
    private SqsService sqsService;

    @Autowired
    private StorageService storageService;

    @Override
    public void buildFollowNotification(String remoteUsername) {
        String myUsername = ((JwtUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
        String title = myUsername + " started following you";
        var newNotification = Notification.buildFollowNotification(
                remoteUsername,
                title,
                storageService.getMediaUrlFromFilename(MediaFilenameBuilder.buildUserStaticImageFilename(myUsername)),
                null,
                myUsername);

        sqsService.sendNotificationMessage(newNotification);
    }

    @Override
    public void buildPostReactionNotification(String remoteUsername, String postId, String postImageUrl, String postOwner) {
        String myUsername = ((JwtUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
        String title = myUsername + " reacted on your post";
        var newNotification = Notification.buildPostReactionNotification(
                remoteUsername,
                title,
                storageService.getMediaUrlFromFilename(MediaFilenameBuilder.buildUserStaticImageFilename(myUsername)),
                postImageUrl,
                postId);
        sqsService.sendNotificationMessage(newNotification, postId, postOwner);

    }

    @Override
    public void buildCommentNotification(String remoteUsername, String comment, String postId, String postImageUrl, String postOwner) {
        String myUsername = ((JwtUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
        String title = myUsername + " commented: " + comment;

        var newNotification = Notification.buildCommentNotification(
                remoteUsername,
                title,
                storageService.getMediaUrlFromFilename(MediaFilenameBuilder.buildUserStaticImageFilename(myUsername)),
                postImageUrl,
                postId
        );

        sqsService.sendNotificationMessage(newNotification, postId, postOwner);

    }

    @Override
    public void buildCommentReactionNotification(String remoteUsername, String comment, String postId, String postImageUrl) {
        String myUsername = ((JwtUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
        String title = myUsername + " liked your comment: " + comment;
        var newNotification = Notification.buildCommentReactionNotification(
                remoteUsername,
                title,
                storageService.getMediaUrlFromFilename(MediaFilenameBuilder.buildUserStaticImageFilename(myUsername)),
                postImageUrl,
                postId);

        sqsService.sendNotificationMessage(newNotification);

    }

    @Override
    public void buildTagRequestNotification(List<UsernameDto> taggedUsers, String postId, String postImageUrl) {
        String myUsername = ((JwtUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
        String title = myUsername + " tagged you. Accept the tag if you like it.";
        var newNotification = Notification.buildTagRequestNotification(
                null,
                title,
                storageService.getMediaUrlFromFilename(MediaFilenameBuilder.buildUserStaticImageFilename(myUsername)),
                postImageUrl,
                postId);

        sqsService.sendNotificationMessage(newNotification, taggedUsers);

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
                storageService.getMediaUrlFromFilename(MediaFilenameBuilder.buildUserStaticImageFilename(myUsername)),
                postImageUrl,
                postId);

        sqsService.sendNotificationMessage(newNotification);

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
