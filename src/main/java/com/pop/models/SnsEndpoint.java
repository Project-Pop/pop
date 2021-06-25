package com.pop.models;

public class SnsEndpoint {
    private String deviceToken;
    private String endpointArn;
    private boolean enabled;

    public SnsEndpoint(String deviceToken, String endpointArn, boolean enabled) {
        this.deviceToken = deviceToken;
        this.endpointArn = endpointArn;
        this.enabled = enabled;
    }

    public String getDeviceToken() {
        return deviceToken;
    }

    public void setDeviceToken(String deviceToken) {
        this.deviceToken = deviceToken;
    }

    public String getEndpointArn() {
        return endpointArn;
    }

    public void setEndpointArn(String endpointArn) {
        this.endpointArn = endpointArn;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}
