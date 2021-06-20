package com.pop.utils;

import com.nimbusds.jwt.JWTClaimsSet;
import com.pop.models.JwtUser;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class JwtAuthentication extends AbstractAuthenticationToken {

    private final JwtUser principal;
    private JWTClaimsSet jwtClaimsSet;

    public JwtAuthentication(JwtUser principal, JWTClaimsSet jwtClaimsSet, Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        this.principal = principal;
        this.jwtClaimsSet = jwtClaimsSet;
        super.setAuthenticated(true);
    }

    public Object getCredentials() {
        return null;
    }

    public JWTClaimsSet getJwtClaimsSet() {
        return jwtClaimsSet;
    }

    public void setJwtClaimsSet(JWTClaimsSet jwtClaimsSet) {
        this.jwtClaimsSet = jwtClaimsSet;
    }

    public JwtUser getPrincipal() {
        return principal;
    }

}
