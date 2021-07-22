package com.pop.models;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UserProfile {
    @NotNull
    @Size(min = 3, max = 25)
    private String username;
    int views;
    int reacts;
    int popScore;
    int followers;
    int following;
    String bio;
    String imageUrl;

    public String getBio() {
        return bio;
    }


    public void setBio(String bio) {
        this.bio = bio;
    }


    public UserProfile() {
    }


    public UserProfile(@NotNull @Size(min = 3, max = 25) String username, int views, int reacts, int popScore, int followers, int following, String bio, String imageUrl) {
        this.username = username;
        this.views = views;
        this.reacts = reacts;
        this.popScore = popScore;
        this.followers = followers;
        this.following = following;
        this.bio = bio;
        this.imageUrl = imageUrl;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getViews() {
        return views;
    }

    public void setViews(int views) {
        this.views = views;
    }

    public int getReacts() {
        return reacts;
    }

    public void setReacts(int reacts) {
        this.reacts = reacts;
    }

    public int getPopScore() {
        return popScore;
    }

    public void setPopScore(int popScore) {
        this.popScore = popScore;
    }

    public int getFollowers() {
        return followers;
    }

    public void setFollowers(int followers) {
        this.followers = followers;
    }

    public int getFollowing() {
        return following;
    }

    public void setFollowing(int following) {
        this.following = following;
    }


}
