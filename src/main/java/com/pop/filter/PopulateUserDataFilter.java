package com.pop.filter;

import com.pop.dao.UserDao;
import com.pop.models.JwtUser;
import com.pop.utils.JwtAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class PopulateUserDataFilter extends OncePerRequestFilter {

    @Autowired
    private UserDao userDao;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        JwtAuthentication authentication = (JwtAuthentication) SecurityContextHolder.getContext().getAuthentication();


        try {
            String username = userDao.getUsernameByUserId(authentication.getPrincipal().getUserId());

            authentication.getPrincipal().setUsername(username);

            SecurityContextHolder.getContext().setAuthentication(authentication);

        } catch (Exception e) {
            System.out.println(e);
        }

        filterChain.doFilter(request, response);
    }
}
