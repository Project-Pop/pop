package com.pop.models;

public class FeedItem {
    private String postId;
    private String imageUrl;
    private String timestamp;

    public FeedItem(String postId, String imageUrl, String timestamp) {
        this.postId = postId;
        this.imageUrl = imageUrl;
        this.timestamp = timestamp;
    }

    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}
