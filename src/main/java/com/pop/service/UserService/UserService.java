package com.pop.service.UserService;

import org.springframework.web.multipart.MultipartFile;

import com.pop.common.Response;
import com.pop.dto.PatchUserDto;
import com.pop.dto.SignUpUserDto;


public interface UserService {

    public Response signUpNewUser(SignUpUserDto signUpUserDto);
    
	public void updateUserImage(MultipartFile image, MultipartFile miniImage);


    public Response editUser(PatchUserDto patchUserDto);

    public Response isUsernameAvailable(String username);


	public Response getUserProfile(String username);

//    public Response searchUsers(String searchString);

    public Response getUserActivities();

    public Response followUser(String username);

    public Response unfollowUser(String username);

}
