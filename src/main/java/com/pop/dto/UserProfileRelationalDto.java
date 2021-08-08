package com.pop.dto;

import com.pop.models.User;

/**
 * Used for sending user related data with respect to principal User.
 */
public class UserProfileRelationalDto {
    private User user;
    private boolean following;

    public UserProfileRelationalDto(User user, boolean following) {
        this.user = user;
        this.following = following;
    }

    public User getUser() {
        return user;
    }

    public boolean isFollowing() {
        return following;
    }
}
