package com.pop.service;

import com.pop.common.Response;
import com.pop.dto.PatchUserDto;
import com.pop.dto.SignUpUserDto;


public interface UserService {

//    public Response signUpNewUser(SignUpUserDto signUpUserDto);

    public Response editUser(PatchUserDto patchUserDto);

    public Response isUsernameAvailable(String username);

//    public Response searchUsers(String searchString);

//    public Response getUserActivities(HttpServletResponse response);

//    public Response getUserProfile(String username);

//    public Response followUser(String username);

//    public Response unfollowUser(String username);

}
