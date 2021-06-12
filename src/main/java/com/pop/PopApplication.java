package com.pop;

import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.jwk.source.RemoteJWKSet;
import com.nimbusds.jose.proc.JWSKeySelector;
import com.nimbusds.jose.proc.JWSVerificationKeySelector;
import com.nimbusds.jose.util.DefaultResourceRetriever;
import com.nimbusds.jose.util.ResourceRetriever;
import com.nimbusds.jwt.proc.ConfigurableJWTProcessor;
import com.nimbusds.jwt.proc.DefaultJWTProcessor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.Executor;

import javax.sql.DataSource;

import static com.nimbusds.jose.JWSAlgorithm.RS256;

@SpringBootApplication(scanBasePackages = {"com.pop", "com.pop.config", "com.pop.controller", "com.pop.dao"})
public class PopApplication {

    public static void main(String[] args) {
        SpringApplication.run(PopApplication.class, args);
    }

    @Bean
    public ConfigurableJWTProcessor getConfigurableJWTProcessor() throws MalformedURLException {
        ResourceRetriever resourceRetriever =
                new DefaultResourceRetriever(2000, 2000);
        URL jwkURL = new URL("https://cognito-idp.ap-south-1.amazonaws.com/ap-south-1_VS8RwdWHw/.well-known/jwks.json");
        JWKSource keySource = new RemoteJWKSet(jwkURL, resourceRetriever);
        ConfigurableJWTProcessor jwtProcessor = new DefaultJWTProcessor();
        JWSKeySelector keySelector = new JWSVerificationKeySelector(RS256, keySource);
        jwtProcessor.setJWSKeySelector(keySelector);
        return jwtProcessor;
    }
    
    

}
