package com.pop.service.AwsClientService;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.model.*;
import com.google.gson.Gson;
import com.pop.models.FeedItem;
import com.pop.models.SnsEndpoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class DynamoDBServiceImpl implements DynamoDBService {

    @Value("${application.dynamodb.generalTableName}")
    private String tableName;

    @Autowired
    private AmazonDynamoDB dynamoDBClient;

    private Map<String, AttributeValue> generateKeyForFeedFromTable(String username) {
        Map<String, AttributeValue> key = new HashMap<>();
        key.put("PK", new AttributeValue("USER#" + username));
        key.put("SK", new AttributeValue("FEED#" + username));
        return key;
    }

    private Map<String, AttributeValue> generateKeyForSNSAttributesFromTable(String username) {
        Map<String, AttributeValue> key = new HashMap<>();
        key.put("PK", new AttributeValue("USER#" + username));
        key.put("SK", new AttributeValue("SNS_DATA#"));
        return key;
    }


    @Override
    public List<FeedItem> fetchUserFeed(String username) {

        GetItemRequest getItemRequest = new GetItemRequest()
                .withTableName(tableName)
                .withKey(generateKeyForFeedFromTable(username))
                .withAttributesToGet("feedItems");
        var data = dynamoDBClient.getItem(getItemRequest).getItem();
        var feedItems = data.get("feedItems");
        if (feedItems != null) {
            return feedItems.getSS()
                    .stream()
                    .map(stringItem -> new Gson().fromJson(stringItem, FeedItem.class))
                    .collect(Collectors.toList());
        }
        return new ArrayList<>();
    }

    @Override
    public SnsEndpoint fetchUserDeviceTokenInfo(String username) {
        GetItemRequest getItemRequest = new GetItemRequest()
                .withTableName(tableName)
                .withKey(generateKeyForFeedFromTable(username))
                .withAttributesToGet("deviceToken", "endpointArn", "enabled");

        var data = dynamoDBClient.getItem(getItemRequest).getItem();
        if (data != null) {
            return new SnsEndpoint(
                    data.get("deviceToken").getS(),
                    data.get("endpointArn").getS(),
                    data.get("enabled").getBOOL());
        }
        return null;
    }

    @Override
    public void putUserDeviceToken(String username, SnsEndpoint snsEndpoint) {
        Map<String, AttributeValue> item = new HashMap<>();
        item.putAll(generateKeyForSNSAttributesFromTable(username));
        item.put("deviceToken", new AttributeValue(snsEndpoint.getDeviceToken()));
        item.put("endpointArn", new AttributeValue(snsEndpoint.getEndpointArn()));
        item.put("enabled", new AttributeValue().withBOOL(snsEndpoint.isEnabled()));

        var request = new PutItemRequest()
                .withTableName(tableName)
                .withItem(item);
        dynamoDBClient.putItem(request);
    }

    @Override
    public void updateUserDeviceToken(String username, SnsEndpoint snsEndpoint) {

        Map<String, AttributeValueUpdate> attr = new HashMap<>();
        attr.put("deviceToken", new AttributeValueUpdate(new AttributeValue(snsEndpoint.getDeviceToken()), "SET"));
        attr.put("endpointArn", new AttributeValueUpdate(new AttributeValue(snsEndpoint.getEndpointArn()), "SET"));
        attr.put("enabled", new AttributeValueUpdate(new AttributeValue().withBOOL(snsEndpoint.isEnabled()), "SET"));

        var request = new UpdateItemRequest()
                .withTableName(tableName)
                .withKey(generateKeyForSNSAttributesFromTable(username))
                .withAttributeUpdates(attr);

        dynamoDBClient.updateItem(request);
    }

    @Override
    public void disableUserDeviceToken(String username) {
        Map<String, AttributeValueUpdate> attr = new HashMap<>();
        attr.put("enabled", new AttributeValueUpdate(new AttributeValue().withBOOL(false), "SET"));

        var request = new UpdateItemRequest()
                .withTableName(tableName)
                .withKey(generateKeyForSNSAttributesFromTable(username))
                .withAttributeUpdates(attr);

        dynamoDBClient.updateItem(request);
    }
}
