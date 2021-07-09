package com.pop.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(
        prefix = "com.pop.jwt.aws"
)

public class JwtConfiguration {
    private String userPoolId;
    private String identityPoolId;
    private String jwkUrl;
    private String region = "ap-south-1";
    private String userIdField = "cognito:username";
    private String phoneField = "phone_number";
    private String httpHeader = "Authorization";

    public String getJwkUrl(){
        return this.jwkUrl !=null && !this.jwkUrl.isEmpty() ? this.jwkUrl : String.format("https://cognito-idp.%s.amazonaws.com/%s/.well-known/jwks.json", this.region, this.userPoolId);
    }

    public String getCognitoIdentityPoolUrl() {
        return String.format("https://cognito-idp.%s.amazonaws.com/%s", this.region, this.userPoolId);
    }

	public String getUserPoolId() {
		return userPoolId;
	}

	public void setUserPoolId(String userPoolId) {
		this.userPoolId = userPoolId;
	}

	public String getIdentityPoolId() {
		return identityPoolId;
	}

	public void setIdentityPoolId(String identityPoolId) {
		this.identityPoolId = identityPoolId;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getUserIdField() {
		return userIdField;
	}

	public void setUserIdField(String userIdField) {
		this.userIdField = userIdField;
	}

    public String getPhoneField() {
        return phoneField;
    }

    public String getHttpHeader() {
		return httpHeader;
	}

	public void setHttpHeader(String httpHeader) {
		this.httpHeader = httpHeader;
	}

	public void setJwkUrl(String jwkUrl) {
		this.jwkUrl = jwkUrl;
	}
    
}
