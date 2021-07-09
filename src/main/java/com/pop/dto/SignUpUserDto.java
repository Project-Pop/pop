package com.pop.dto;

import io.swagger.annotations.ApiOperation;

import java.util.Date;

public class SignUpUserDto {
    private String username;
    private String fullname;
    private String email;
    private String phone;
    private Date dob;

    public SignUpUserDto(String username, String fullname, String email, String phone, Date dob) {
        this.username = username;
        this.fullname = fullname;
        this.email = email;
        this.phone = phone;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

}
