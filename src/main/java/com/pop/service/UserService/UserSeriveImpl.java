package com.pop.service.UserService;

import com.pop.common.Response;
import com.pop.dao.UserDao;
import com.pop.dao.UserProfileDao;
import com.pop.dto.PatchUserDto;
import com.pop.dto.SignUpUserDto;
import com.pop.models.JwtUser;
import com.pop.models.User;
import com.pop.service.AwsClientService.StorageService;
import com.pop.service.NotificationService.NotificationService;
import com.pop.utils.MediaFilenameBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

@Service
public class UserSeriveImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private UserProfileDao userProfileDao;

    @Autowired
    private StorageService storageService;

    @Autowired
    private NotificationService notificationService;

    @Override
    public Response isUsernameAvailable(String username) {
        boolean availability = userDao.isUserNameAvailable(username);
        return new Response(availability, "", HttpServletResponse.SC_OK);
    }

    @Override
    public Response editUser(PatchUserDto patchUserDto) {
        User updatedUser = new User();
        var principalUser = (JwtUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        var oldUser = userDao.getUserByUsername(principalUser.getUsername());

        if (patchUserDto.getFullname() != null) {
            updatedUser.setFullname(patchUserDto.getFullname());
        } else {

            updatedUser.setFullname(oldUser.getFullname());
        }

        if (patchUserDto.getEmail() != null) {
            updatedUser.setEmail(patchUserDto.getEmail());
        } else {
            updatedUser.setEmail(oldUser.getEmail());
        }

        if (patchUserDto.getDob() != null) {
            updatedUser.setDob(patchUserDto.getDob());
        } else {
            updatedUser.setDob(oldUser.getDob());
        }

        userDao.updateUser(updatedUser);

        return new Response(patchUserDto, "updated", HttpServletResponse.SC_OK);
    }

    @Override
    public Response signUpNewUser(SignUpUserDto user) {
        var principalUser = (JwtUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        var newUser = new User(
                principalUser.getUserId(),
                user.getUsername(),
                user.getFullname(),
                user.getEmail(),
                principalUser.getPhone(),
                user.getDob()
        );

        try {
            userDao.saveUser(newUser);
            userProfileDao.createProfile(user.getUsername());
        } catch (DuplicateKeyException e) {
            return Response.error(e.getCause().getLocalizedMessage(), HttpServletResponse.SC_BAD_REQUEST);
        } catch (DataAccessException e) {
            return Response.error(e.getCause().getLocalizedMessage(), HttpServletResponse.SC_BAD_REQUEST);
        }
        return new Response(user, "USER ADDED", HttpServletResponse.SC_OK);
    }

    @Override
    public Response registerUserDeviceToken(String deviceToken) {
        return null;
    }

    @Override
    public Response getUserProfile(String username) {
        try {
            User u = userDao.getUserByUsername(username);
            return new Response(u, "USER FETCHED", HttpServletResponse.SC_OK);
        } catch (Exception e) {
            return Response.error(e.getCause().getLocalizedMessage(), HttpServletResponse.SC_BAD_REQUEST);
        }
    }

    @Override
    public Response followUser(String username) {
        var principalUser = (JwtUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String myUsername = principalUser.getUsername();
        try {
            userProfileDao.followUser(username, myUsername);

            //sending notification
            notificationService.buildFollowNotification(username);

        } catch (Exception e) {
            System.out.println(e.getCause().getLocalizedMessage());
            return Response.error(e.getCause().getLocalizedMessage(), HttpServletResponse.SC_BAD_REQUEST);
        }
        return Response.ok("You are following ${username}", HttpServletResponse.SC_BAD_REQUEST);
    }

    @Override
    public Response unfollowUser(String username) {
        var principalUser = (JwtUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String myUsername = principalUser.getUsername();
        try {
            userProfileDao.unFollowUser(username, myUsername);
        } catch (Exception e) {
            return new Response(e.getCause().getLocalizedMessage(), HttpServletResponse.SC_BAD_REQUEST);
        }
        return Response.ok("Succedeed", 400);
    }

    @Override
    public void updateUserImage(MultipartFile image, MultipartFile miniImage) {
        var principalUser = (JwtUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String myUsername = principalUser.getUsername();
        String imageUrl = userProfileDao.getProfileImageUrl(myUsername);
//        System.out.println(imageUrl.length());
        if (imageUrl != null && imageUrl.length() != 0) {
            storageService.deleteFile(imageUrl);
        }

// uploading original size user profile image
        String newDynamicImageFilename = MediaFilenameBuilder.buildUserDynamicImageFilename();
        String newDynamicImageUrl = storageService.uploadFile(image, newDynamicImageFilename);
        userProfileDao.updateImageUrl(newDynamicImageUrl, myUsername);

// uploading thumbnail purpose profile image
        String thumbnailFilename = MediaFilenameBuilder.buildUserStaticImageFilename(myUsername);
        storageService.uploadFile(miniImage, thumbnailFilename);
    }

    @Override
    public Response getUserActivities() {

        var notifications = notificationService.getNotifications();

        return new Response(notifications, "notifications fetched", HttpServletResponse.SC_OK);
    }
}
