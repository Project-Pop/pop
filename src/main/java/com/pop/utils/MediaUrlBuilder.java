package com.pop.utils;

import java.util.UUID;

public class MediaUrlBuilder {
    private static String basePrefix = "prefix";

    private static String userPrefix = "userAvatar";
    private static String postPrefix = "postMedia";

    public static String buildUserStaticImageUrl(String username) {
        return basePrefix + "/" + userPrefix + "/" + username;
    }

    /*
    This returns new url to update the avatar of currently logged in user
     */
    public static String buildUserDynamicImageUrl() {
        return basePrefix + "/" + userPrefix + "/" + UUID.randomUUID().toString();
    }

    public static String buildPostMediaUrl(String postId) {
        return basePrefix + "/" + postPrefix + "/" + postId;
    }

}
