package com.pop.service;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.pop.common.Response;
import com.pop.dao.UserDao;
import com.pop.dao.UserProfileDao;
import com.pop.dto.PatchUserDto;
import com.pop.models.User;

@Service
public class UserSeriveImpl implements UserService {

    @Autowired
    private UserDao userDao;
    
    @Autowired
    private UserProfileDao userProfileDao;

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

	@Override
	public Response signUpNewUser(User user) {
		var principalUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		user.setUserId(principalUser.getUserId());
		try {
	        userDao.saveUser(user);
	        userProfileDao.createProfile(user.getUsername());
	        
	    } catch(DuplicateKeyException e) {
	    	return new Response(e.getCause().getLocalizedMessage(), HttpServletResponse.SC_BAD_REQUEST);
	    } catch(DataAccessException e) {
	    	return new Response(e.getCause().getLocalizedMessage(), HttpServletResponse.SC_BAD_REQUEST);
	    }
		return new Response(user, "USER ADDED", HttpServletResponse.SC_OK);
	}
	
	@Override
	public Response getUserProfile(String username) {
		try {
			User u = userDao.getUserByUsername(username);
			return new Response(u, "USER FETCHED", HttpServletResponse.SC_OK);
		} catch (Exception e) {
	    	return new Response(e.getCause().getLocalizedMessage(), HttpServletResponse.SC_BAD_REQUEST);
		}
	}

	@Override
	public Response followUser(String username) {
		var principalUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String myUsername = principalUser.getUsername();
		try {
			userProfileDao.followUser(username, myUsername);
		}catch (Exception e) {
	    	return new Response(e.getCause().getLocalizedMessage(), HttpServletResponse.SC_BAD_REQUEST);
		}
		return Response.ok("You are following ${username}", 400);
	}

	@Override
	public Response unfollowUser(String username) {
		var principalUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String myUsername = principalUser.getUsername();
		try {
			userProfileDao.unFollowUser(username, myUsername);
		}catch (Exception e) {
	    	return new Response(e.getCause().getLocalizedMessage(), HttpServletResponse.SC_BAD_REQUEST);
		}
		return Response.ok("Succedeed", 400);
	}
}
