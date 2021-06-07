package com.pop.utils;

import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.proc.ConfigurableJWTProcessor;
import com.pop.config.JwtConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
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
        if (idToken != null) {
            JWTClaimsSet claims = this.configurableJWTProcessor
                    .process(this.getBearerToken(idToken), null);
            validateIssuer(claims);
            verifyIdToken(claims);

            String username = getUserNameFrom(claims);

            if (username != null) {

                List<GrantedAuthority> grantedAuthorities = of( new SimpleGrantedAuthority("ROLE_USER"));
                User user = new User(username, "", of());
                return new JwtAuthentication(user, claims, grantedAuthorities);

            }
        }
        return null;
    }



    private String getUserNameFrom(JWTClaimsSet claims) {
        return claims.getClaims()
                .get(this.jwtConfiguration.getUserNameField())
                .toString();
    }

    private void verifyIdToken(JWTClaimsSet claims) throws Exception{
        if(!claims.getIssuer().equals(this.jwtConfiguration.getCognitoIdentityPoolUrl())){
            throw new Exception("Not an ID Token");
        }
    }


    private void validateIssuer(JWTClaimsSet claims) throws Exception {
        if (!claims.getIssuer().equals(this.jwtConfiguration.getCognitoIdentityPoolUrl())) {
            throw new Exception(String.format("Issuer %s does not match cognito idp %s", claims.getIssuer(), this.jwtConfiguration.getCognitoIdentityPoolUrl()));
        }
    }


    private String getBearerToken(String token){
        return token.startsWith("Bearer ")? token.substring("Bearer ".length()):token;
    }

}
