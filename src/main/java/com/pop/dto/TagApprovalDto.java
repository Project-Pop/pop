package com.pop.dto;

public class TagApprovalDto {
    private boolean isApproved;

    public TagApprovalDto(boolean isApproved) {
        this.isApproved = isApproved;
    }

    public boolean isApproved() {
        return isApproved;
    }
}
