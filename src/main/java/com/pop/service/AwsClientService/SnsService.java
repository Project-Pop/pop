package com.pop.service.AwsClientService;

public interface SnsService {
    String registerNewDeviceToken(String deviceToken);

    void updateDeviceToken(String endpointArn, String deviceToken);

    void disableDeviceToken(String endpointArn);
}
