package com.pop.controller;

import com.pop.dto.PatchUserDto;
import com.pop.dto.SignUpUserDto;
import com.pop.dto.UsernameDto;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/users")
public class UserController {

    @PostMapping("/")
    void signUpNewUser(@RequestBody SignUpUserDto signUpUserDto) {
        return;
    }


    @PatchMapping("/")
    void editUserProfile(@RequestBody PatchUserDto patchUserDto) {
        return;
    }


    @GetMapping("/activities")
    void getUserActivities() {
        return;
    }

    @GetMapping("/{username}")
    void getUserProfile(@PathVariable String username) {
        return;
    }

    @PostMapping("/{username}/follow")
    void followUser(@PathVariable String username) {
        return;
    }


    @PostMapping("/{username}/unfollow")
    void unfollowUser(@PathVariable String username) {
        return;
    }


    @PostMapping("/username-availability")
    void isUsernameAvailable(@RequestBody UsernameDto usernameDto) {
        return;
    }

    @GetMapping("/search")
    void searchUsers(HttpServletRequest request) {
        String text = request.getParameter("text");
        return;
    }


}
