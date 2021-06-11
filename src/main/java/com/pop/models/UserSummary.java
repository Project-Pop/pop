package com.pop.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserSummary {
    private String userId;
    private String name;
    private String username;
    private String avatar;
}
