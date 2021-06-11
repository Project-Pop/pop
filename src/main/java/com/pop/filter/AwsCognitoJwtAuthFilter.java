package com.pop.filter;

import com.pop.utils.AwsCognitoIdTokenProcessor;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class AwsCognitoJwtAuthFilter extends OncePerRequestFilter {

    private static final Log logger = LogFactory.getLog(AwsCognitoJwtAuthFilter.class);

    @Autowired
    private AwsCognitoIdTokenProcessor cognitoIdTokenProcessor;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        Authentication authentication;
        try {
            authentication = this.cognitoIdTokenProcessor.authenticate((HttpServletRequest) request);

            if (authentication != null) {
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        } catch (Exception var6) {
            logger.error("Cognito ID Token processing error", var6);
            SecurityContextHolder.clearContext();
        }

        chain.doFilter(request, response);
    }
}
