package com.pop.controller;

import com.pop.common.Response;
import com.pop.dto.*;
import com.pop.models.Notification;
import com.pop.models.User;
import com.pop.service.UserService.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@ApiOperation(value = "/users", tags = "User controller")
@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/")
    UserProfileRelationalDto signUpNewUser(@RequestBody SignUpUserDto user, HttpServletResponse response) throws IOException {
        var res = userService.signUpNewUser(user);
        if (res.isContainsError()) {
            response.sendError(res.getCode(), res.getError());
        } else response.setStatus(res.getCode());

        return (UserProfileRelationalDto) res.getData();
    }

    @PostMapping("/avatar")
    void uploadImage(MultipartFile image, MultipartFile miniImage,HttpServletRequest request) {

        userService.updateUserImage(image, miniImage);
    }


    @PatchMapping("/")
    PatchUserDto editUserProfile(@RequestBody PatchUserDto patchUserDto, HttpServletResponse response) throws IOException {
        var res = userService.editUser(patchUserDto);

        if (res.isContainsError()) {
            response.sendError(res.getCode(), res.getError());
        } else {
            response.setStatus(res.getCode());
        }

        return (PatchUserDto) res.getData();

    }

    @GetMapping("/")
    User getPrincipalUserProfile(HttpServletResponse response) throws IOException {
        Response res = userService.getPrincipalUserProfile();
        if (res.isContainsError()) {
            response.sendError(res.getCode(), res.getError());
        }
//        System.out.println(res.getData());

        return (User) res.getData();
    }

    @PostMapping("/device-token")
    void registerDeviceToken(@RequestParam String deviceToken, HttpServletResponse response) throws IOException {
        var res = userService.registerUserDeviceToken(deviceToken);


        if (res.isContainsError()) {
            response.sendError(res.getCode(), res.getError());
        } else {
            response.setStatus(res.getCode());
        }
        return;
    }

    @DeleteMapping("/device-token")
    void deleteDeviceToken(HttpServletResponse response) throws IOException {
        var res = userService.disableUserDeviceToken();

        if (res.isContainsError()) {
            response.sendError(res.getCode(), res.getError());
        } else {
            response.setStatus(res.getCode());
        }
        return;
    }

    @PostMapping("/username-availability")
    boolean isUsernameAvailable(@RequestParam String username, HttpServletResponse response) throws IOException {
        var res = userService.isUsernameAvailable(username);
        if (res.isContainsError()) {
            response.sendError(res.getCode(), res.getError());
        } else {
            response.setStatus(res.getCode());
        }

        return (boolean) res.getData();
    }

    @GetMapping("/search")
    List<MinimalUserDto> searchUsers(@RequestParam String searchString, HttpServletResponse response) throws IOException {
        var res = userService.searchUsers(searchString);
        if(res.isContainsError()){
            response.sendError(res.getCode(),res.getError());
        }else{
            response.setStatus(res.getCode());
        }
        return (List<MinimalUserDto>) res.getData();
    }


    @GetMapping("/activities")
    List<Notification> getUserActivities(HttpServletResponse response) throws IOException {
        Response res = userService.getUserActivities();
        if (res.isContainsError()) {
            response.sendError(res.getCode(), res.getError());
        } else {
            response.setStatus(res.getCode());
        }
        return (List<Notification>) res.getData();
    }

    @GetMapping("/{username}")
    UserProfileRelationalDto getUserProfileRelationalData(@PathVariable String username, HttpServletResponse response) throws IOException {
        Response res = userService.getUserProfileRelationalData(username);
        if (res.isContainsError()) {
            response.sendError(res.getCode(), res.getError());
        }
//        System.out.println(res.getData());

        return (UserProfileRelationalDto) res.getData();
    }

    @PostMapping("/{username}/follow")
    void followUser(@PathVariable String username, HttpServletResponse response) throws IOException {
        Response res = userService.followUser(username);
        if (res.isContainsError()) {
            response.sendError(res.getCode(), res.getError());
        }
    }


    @PostMapping("/{username}/unfollow")
    void unfollowUser(@PathVariable String username, HttpServletResponse response) throws IOException {
        Response res = userService.unfollowUser(username);
        if (res.isContainsError()) {
            response.sendError(res.getCode(), res.getError());
        }
    }

}
