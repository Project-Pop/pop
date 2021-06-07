package com.pop.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SimpleController {

    @GetMapping("/api/hello")
    public String getResp(){
        return "Hey authenticated request";
    }
}
