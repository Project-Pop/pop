package com.pop.utils;

import java.util.Collection;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import com.nimbusds.jwt.JWTClaimsSet;
import com.pop.models.UserSummary;

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

	public JWTClaimsSet getJwtClaimsSet() {
		return jwtClaimsSet;
	}

	public void setJwtClaimsSet(JWTClaimsSet jwtClaimsSet) {
		this.jwtClaimsSet = jwtClaimsSet;
	}

	public UserSummary getPrincipal() {
		return principal;
	}
    
}
