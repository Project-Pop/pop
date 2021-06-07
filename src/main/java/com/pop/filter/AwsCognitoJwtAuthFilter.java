package com.pop.filter;

import com.pop.utils.AwsCognitoIdTokenProcessor;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Component
public class AwsCognitoJwtAuthFilter extends GenericFilter {

    private static final Log logger = LogFactory.getLog(AwsCognitoJwtAuthFilter.class);

    private AwsCognitoIdTokenProcessor cognitoIdTokenProcessor;

    public AwsCognitoJwtAuthFilter(AwsCognitoIdTokenProcessor cognitoIdTokenProcessor) {
        this.cognitoIdTokenProcessor = cognitoIdTokenProcessor;
    }


    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
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
