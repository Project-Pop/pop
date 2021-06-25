package com.pop.service.AwsClientService;

import com.pop.models.FeedItem;
import com.pop.models.SnsEndpoint;

import java.util.List;

public interface DynamoDBService {

    List<FeedItem> fetchUserFeed(String username);

    SnsEndpoint fetchUserDeviceTokenInfo(String username);

    void putUserDeviceToken(String username, SnsEndpoint snsEndpoint);

    void updateUserDeviceToken(String username, SnsEndpoint snsEndpoint);

    void disableUserDeviceToken(String username);

}
