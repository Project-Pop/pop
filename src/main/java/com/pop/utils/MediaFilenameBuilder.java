package com.pop.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.print.attribute.standard.Media;
import java.util.UUID;

public class MediaFilenameBuilder {

    @Value("${application.mediaUrl.basePrefix}")
    private String basePrefix;

    private String userPrefix = "userAvatar";
    private String postPrefix = "postMedia";

    private static MediaFilenameBuilder instance = new MediaFilenameBuilder();

    public static String buildUserStaticImageFilename(String username) {
        return instance.basePrefix + "/" + instance.userPrefix + "/" + username;
    }

    /*
    This returns new filename to update the avatar of currently logged in user
     */
    public static String buildUserDynamicImageFilename() {
        return instance.basePrefix + "/" + instance.userPrefix + "/" + UUID.randomUUID().toString();
    }

    public static String buildPostMediaFilename(String postId) {
        return instance.basePrefix + "/" + instance.postPrefix + "/" + postId;
    }

}
