package com.pop.controller;

import com.pop.dto.PatchUserDto;
import com.pop.dto.SignUpUserDto;
import com.pop.dto.UsernameDto;
import com.pop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping("/v1/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/")
    void signUpNewUser(@RequestBody SignUpUserDto signUpUserDto, HttpServletResponse response) {
        return;
    }


    @PatchMapping("/")
    PatchUserDto editUserProfile(@RequestBody PatchUserDto patchUserDto, HttpServletResponse response) throws IOException {
        var res = userService.editUser(patchUserDto);

        if(res.isContainsError()){
            response.sendError(res.getCode(),res.getError());
        }else{
            response.setStatus(res.getCode());
        }

        return (PatchUserDto) res.getData();

    }

    @PostMapping("/username-availability")
    boolean isUsernameAvailable(@RequestBody UsernameDto usernameDto, HttpServletResponse response) throws IOException {

        var res = userService.isUsernameAvailable(usernameDto.getUsername());

        if(res.isContainsError()){
            response.sendError(res.getCode(),res.getError());
        }else{
            response.setStatus(res.getCode());
        }

        return (boolean) res.getData();
    }

    @GetMapping("/search")
    void searchUsers(HttpServletRequest request) {
        String text = request.getParameter("text");
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

}
