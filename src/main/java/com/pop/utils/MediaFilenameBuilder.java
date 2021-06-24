package com.pop.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class MediaFilenameBuilder {

    @Value("${application.mediaUrl.basePrefix}")
    private static String basePrefix;

    private static String userPrefix = "userAvatar";
    private static String postPrefix = "postMedia";


    public static String buildUserStaticImageFilename(String username) {
        return basePrefix + "/" + userPrefix + "/" + username;
    }

    /*
    This returns new filename to update the avatar of currently logged in user
     */
    public static String buildUserDynamicImageFilename() {
        return basePrefix + "/" + userPrefix + "/" + UUID.randomUUID().toString();
    }

    public static String buildPostMediaFilename(String postId) {
        return basePrefix + "/" + postPrefix + "/" + postId;
    }

}
