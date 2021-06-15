package com.pop.models;

import javax.validation.constraints.NotNull;

public class Tagged {

    @NotNull
    String username;
    @NotNull
    String postId;
    boolean approvalStatus;

    public Tagged(@NotNull String username, @NotNull String postId) {
        super();
        this.username = username;
        this.postId = postId;
        this.approvalStatus = false;
    }


    public Tagged(@NotNull String username, @NotNull String postId, boolean approvalStatus) {
        super();
        this.username = username;
        this.postId = postId;
        this.approvalStatus = approvalStatus;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }

    public boolean isApprovalStatus() {
        return approvalStatus;
    }

    public void setApprovalStatus(boolean approvalStatus) {
        this.approvalStatus = approvalStatus;
    }


}
