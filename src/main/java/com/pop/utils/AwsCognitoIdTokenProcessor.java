package com.pop.utils;

import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.proc.ConfigurableJWTProcessor;
import com.pop.config.JwtConfiguration;
import com.pop.models.JwtUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static java.util.List.of;

@Component
public class AwsCognitoIdTokenProcessor {

    @Autowired
    private JwtConfiguration jwtConfiguration;

    @Autowired
    private ConfigurableJWTProcessor configurableJWTProcessor;

    public Authentication authenticate(HttpServletRequest request) throws Exception {

        String idToken = request.getHeader(this.jwtConfiguration.getHttpHeader());

        System.out.println(idToken);

        JWTClaimsSet claims = this.configurableJWTProcessor
                .process(this.getBearerToken(idToken), null);

        validateIssuer(claims);
        isIdToken(claims);


        String userId = claims.getClaims().get(this.jwtConfiguration.getUserIdField()).toString();
        String phone = claims.getClaims().get(this.jwtConfiguration.getPhoneField()).toString();


        JwtUser principalUser = new JwtUser(userId, phone);

        return new JwtAuthentication(principalUser, claims, null);

    }


    private String getUserIdFrom(JWTClaimsSet claims) {
        return claims.getClaims()
                .get(this.jwtConfiguration.getUserIdField())
                .toString();
    }


    private void isIdToken(JWTClaimsSet claims) throws Exception {
        if (!claims.getClaim("token_use").equals("id")) {
            throw new Exception("Not an ID Token");
        }
    }


    private void validateIssuer(JWTClaimsSet claims) throws Exception {
        if (!claims.getIssuer().equals(this.jwtConfiguration.getCognitoIdentityPoolUrl())) {
            throw new Exception(String.format("Issuer %s does not match cognito idp %s", claims.getIssuer(), this.jwtConfiguration.getCognitoIdentityPoolUrl()));
        }
    }


    private String getBearerToken(String token) {
        return token.startsWith("Bearer ") ? token.substring("Bearer ".length()) : token;
    }

}
