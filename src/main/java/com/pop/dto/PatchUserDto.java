package com.pop.dto;

import java.util.Date;

public class PatchUserDto {
    private String fullname;
    private String email;
    private Date dob;

    public PatchUserDto(String fullname, String email, Date dob) {
        this.fullname = fullname;
        this.email = email;
        this.dob = dob;
    }

    public String getFullname() {
        return fullname;
    }

    public String getEmail() {
        return email;
    }

    public Date getDob() {
        return dob;
    }
}
