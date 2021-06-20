package com.pop.models;

public class JwtUser {
    private String userId;
    private String username;
    private String phone;

    public JwtUser(String userId, String phone) {
        this.userId = userId;
        this.phone = phone;
    }

    public String getUserId() {
        return userId;
    }

    public String getPhone() {
        return phone;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
