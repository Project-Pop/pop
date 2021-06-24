package com.pop.service.AwsClientService;

import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.model.MessageAttributeValue;
import com.amazonaws.services.sqs.model.SendMessageRequest;
import com.google.gson.Gson;
import com.pop.dto.UsernameDto;
import com.pop.models.Notification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class SqsService {
    @Autowired
    private AmazonSQS sqsClient;

    @Value("${application.sqs.notificationQueueUrl}")
    private String notificationQueueUrl;

    @Value("${application.sqs.feedGeneratorQueueUrl}")
    private String feedGeneratorQueueUrl ;


    public void sendNotificationMessage(Notification notification) {
        var messageRequest = new SendMessageRequest()
                .withQueueUrl(notificationQueueUrl)
                .withMessageBody("Message from Pop (Java)")
                .addMessageAttributesEntry("notification",
                        new MessageAttributeValue().withDataType("string")
                                .withStringValue(new Gson().toJson(notification)))
                .withMessageDeduplicationId(notification.getNotificationId())
                .withMessageGroupId(notification.getTargetResourceId());

        sqsClient.sendMessage(messageRequest);
    }


    public void sendNotificationMessage(Notification notification, List<UsernameDto> taggedUsers) {
        var messageRequest = new SendMessageRequest()
                .withQueueUrl(notificationQueueUrl)
                .withMessageBody("Message from Pop (Java)")
                .addMessageAttributesEntry("notification",
                        new MessageAttributeValue().withDataType("string")
                                .withStringValue(new Gson().toJson(notification)))
                .addMessageAttributesEntry("taggedUsers",
                        new MessageAttributeValue().withDataType("string")
                                .withStringValue(new Gson().toJson(taggedUsers)))
                .withMessageDeduplicationId(notification.getNotificationId())
                .withMessageGroupId(notification.getTargetResourceId());

        sqsClient.sendMessage(messageRequest);
    }


    public void sendNotificationMessage(Notification notification, String postId, String owner) {
        var messageRequest = new SendMessageRequest()
                .withQueueUrl(notificationQueueUrl)
                .withMessageBody("Message from Pop (Java)")
                .addMessageAttributesEntry("notification",
                        new MessageAttributeValue().withDataType("string")
                                .withStringValue(new Gson().toJson(notification)))
                .addMessageAttributesEntry("postId",
                        new MessageAttributeValue().withDataType("string")
                                .withStringValue(postId))
                .addMessageAttributesEntry("owner",
                        new MessageAttributeValue().withDataType("string")
                                .withStringValue(owner))
                .withMessageDeduplicationId(notification.getNotificationId())
                .withMessageGroupId(notification.getTargetResourceId());

        sqsClient.sendMessage(messageRequest);
    }

    public void sendPostForFeedGeneration(String username, String postId, String imageUrl, Date timestamp) {
        var messageRequest = new SendMessageRequest()
                .withQueueUrl(feedGeneratorQueueUrl)
                .withMessageBody("Message from Pop (Java)")
                .addMessageAttributesEntry("username",
                        new MessageAttributeValue().withDataType("string")
                                .withStringValue(username))
                .addMessageAttributesEntry("postId",
                        new MessageAttributeValue().withDataType("string")
                                .withStringValue(postId))
                .addMessageAttributesEntry("imageUrl",
                        new MessageAttributeValue().withDataType("string")
                                .withStringValue(imageUrl))
                .addMessageAttributesEntry("timestamp",
                        new MessageAttributeValue().withDataType("string")
                                .withStringValue("" + timestamp.getTime()))
                .withMessageDeduplicationId(postId)
                .withMessageGroupId(username);

        sqsClient.sendMessage(messageRequest);

    }

}
