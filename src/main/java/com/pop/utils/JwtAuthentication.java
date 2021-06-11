package com.pop.utils;

import com.nimbusds.jwt.JWTClaimsSet;
import com.pop.models.UserSummary;
import lombok.Getter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import javax.security.auth.Subject;
import java.util.Collection;

@Getter
public class JwtAuthentication extends AbstractAuthenticationToken {

    private final UserSummary principal;
    private JWTClaimsSet jwtClaimsSet;

    public JwtAuthentication(UserSummary principal, JWTClaimsSet jwtClaimsSet, Collection<? extends GrantedAuthority> authorities){
        super(authorities);
        this.principal = principal;
        this.jwtClaimsSet = jwtClaimsSet;
        super.setAuthenticated(true);
    }

    public Object getCredentials() {
        return null;
    }

}
