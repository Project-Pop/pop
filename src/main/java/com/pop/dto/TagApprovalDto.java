package com.pop.dto;

import org.springframework.lang.NonNull;

public class TagApprovalDto {
    @NonNull
    private boolean isApproved;

    public TagApprovalDto(boolean isApproved) {
        this.isApproved = isApproved;
    }

    public boolean isApproved() {
        return isApproved;
    }
}
