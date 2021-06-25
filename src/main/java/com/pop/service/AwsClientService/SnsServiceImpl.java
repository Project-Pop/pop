package com.pop.service.AwsClientService;

import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.model.CreatePlatformEndpointRequest;
import com.amazonaws.services.sns.model.SetEndpointAttributesRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class SnsServiceImpl implements SnsService {

    @Autowired
    private AmazonSNS snsClient;


    @Value("${application.sns.PlatformApplicationArn}")
    private String platformApplicationArn;

    @Override
    public String registerNewDeviceToken(String deviceToken) {
        var attributes = new HashMap<String, String>();
        attributes.put("Enabled", "true");
        attributes.put("Token", deviceToken);

        var endpointRequest = new CreatePlatformEndpointRequest()
                .withPlatformApplicationArn(platformApplicationArn)
                .withToken(deviceToken)
                .withAttributes(attributes);
        var res = snsClient.createPlatformEndpoint(endpointRequest);
        return res.getEndpointArn();
    }

    @Override
    public void updateDeviceToken(String endpointArn, String deviceToken) {
        var attributes = new HashMap<String, String>();
        attributes.put("Enabled", "true");
        attributes.put("Token", deviceToken);

        var request = new SetEndpointAttributesRequest()
                .withEndpointArn(endpointArn)
                .withAttributes(attributes);

        snsClient.setEndpointAttributes(request);
    }

    @Override
    public void disableDeviceToken(String endpointArn) {
        var attributes = new HashMap<String, String>();
        attributes.put("Enabled", "false");

        var request = new SetEndpointAttributesRequest()
                .withEndpointArn(endpointArn)
                .withAttributes(attributes);

        snsClient.setEndpointAttributes(request);

    }
}
