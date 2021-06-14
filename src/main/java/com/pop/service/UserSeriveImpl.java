package com.pop.service;

import com.pop.common.Response;
import com.pop.dao.UserDao;
import com.pop.dto.PatchUserDto;
import com.pop.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;

@Service
public class UserSeriveImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public Response isUsernameAvailable(String username) {
        boolean availability = userDao.isUserNameAvailable(username);
        return new Response( availability,"",HttpServletResponse.SC_OK );
    }

    @Override
    public Response editUser(PatchUserDto patchUserDto) {
        User updatedUser = new User();

        var principalUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (patchUserDto.getFullname() != null) {
            updatedUser.setFullname(patchUserDto.getFullname());
        } else {
            updatedUser.setFullname(principalUser.getFullname());
        }

        if (patchUserDto.getEmail() != null) {
            updatedUser.setEmail(patchUserDto.getEmail());
        } else {
            updatedUser.setEmail(principalUser.getEmail());
        }

        if (patchUserDto.getDob() != null) {
            updatedUser.setDob(patchUserDto.getDob());
        } else {
            updatedUser.setDob(principalUser.getDob());
        }

        userDao.updateUser(updatedUser);

        return new Response(patchUserDto, "updated",HttpServletResponse.SC_OK);
    }


}
