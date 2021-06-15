package com.pop.service;

import org.springframework.web.multipart.MultipartFile;

import com.pop.common.Response;
import com.pop.dto.PatchUserDto;
import com.pop.dto.SignUpUserDto;
import com.pop.models.User;


public interface UserService {

    public Response signUpNewUser(User signUpUserDto);
    
	public void updateUserImage(MultipartFile image, MultipartFile miniImage);


    public Response editUser(PatchUserDto patchUserDto);

    public Response isUsernameAvailable(String username);


	public Response getUserProfile(String username);

//    public Response searchUsers(String searchString);

//    public Response getUserActivities(HttpServletResponse response);

    public Response followUser(String username);

    public Response unfollowUser(String username);

}
