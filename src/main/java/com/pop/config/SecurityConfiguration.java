package com.pop.config;

import com.pop.filter.AwsCognitoJwtAuthFilter;
import com.pop.filter.PopulateUserDataFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Autowired
    private AwsCognitoJwtAuthFilter awsCognitoJwtAuthFilter;

    @Autowired
    private PopulateUserDataFilter populateUserDataFilter;

    private final String[] AUTH_WHITELIST = {"/v2/api-docs",
            "/configuration/ui",
            "/swagger-resources/**",
            "/configuration/**",
            "/swagger-ui.html",
            "/webjars/**"};

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.headers().cacheControl();
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers(AUTH_WHITELIST).permitAll()
                .anyRequest().authenticated()
                .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilterBefore(awsCognitoJwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(populateUserDataFilter, UsernamePasswordAuthenticationFilter.class)
        ;
    }
}
