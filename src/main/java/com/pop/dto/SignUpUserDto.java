package com.pop.dto;

import org.springframework.lang.Nullable;

import java.util.Date;

public class SignUpUserDto {
    private String username;
    private String fullname;

    @Nullable
    private String email;

    @Nullable
    private Date dob;

    public SignUpUserDto(String username, String fullname, String email, Date dob) {
        this.username = username;
        this.fullname = fullname;
        this.email = email;
        this.dob = dob;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

}
