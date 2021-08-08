package com.pop.service.UserService;

import com.pop.common.Response;
import com.pop.dto.PatchUserDto;
import com.pop.dto.SignUpUserDto;
import org.springframework.web.multipart.MultipartFile;


public interface UserService {

    public Response signUpNewUser(SignUpUserDto signUpUserDto);

    public Response registerUserDeviceToken(String deviceToken);

    public Response disableUserDeviceToken();

    public void updateUserImage(MultipartFile image, MultipartFile miniImage);


    public Response editUser(PatchUserDto patchUserDto);

    public Response isUsernameAvailable(String username);


    public Response getUserProfileRelationalData(String username);

    public Response getPrincipalUserProfile();


    public Response searchUsers(String searchString);

    public Response getUserActivities();

    public Response followUser(String username);

    public Response unfollowUser(String username);

}
