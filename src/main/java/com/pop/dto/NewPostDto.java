package com.pop.dto;

import java.util.List;

public class NewPostDto {
    private String description;
    private List<UsernameDto> taggedUsers;

    public NewPostDto(String description, List<UsernameDto> taggedUsers) {
        this.description = description;
        this.taggedUsers = taggedUsers;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<UsernameDto> getTaggedUsers() {
        return taggedUsers;
    }

    public void setTaggedUsers(List<UsernameDto> taggedUsers) {
        this.taggedUsers = taggedUsers;
    }
}
