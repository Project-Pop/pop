package com.pop.dto;

/**
 * Used for sending only username,fullname and imageUrl of any user
 */
public class MinimalUserDto {
    private String username;
    private String fullname;
    private String imageUrl;

    public MinimalUserDto(String username, String fullname, String imageUrl) {
        this.username = username;
        this.fullname = fullname;
        this.imageUrl = imageUrl;
    }

    public String getUsername() {
        return username;
    }

    public String getFullname() {
        return fullname;
    }

    public String getImageUrl() {
        return imageUrl;
    }
}
