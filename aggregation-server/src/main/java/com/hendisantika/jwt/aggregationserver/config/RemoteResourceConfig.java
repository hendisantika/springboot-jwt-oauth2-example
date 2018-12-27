package com.hendisantika.jwt.aggregationserver.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.client.token.grant.client.ClientCredentialsResourceDetails;

/**
 * Created by IntelliJ IDEA.
 * Project : springboot-jwt-oauth2-example
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 2018-12-28
 * Time: 06:52
 * To change this template use File | Settings | File Templates.
 */
@Configuration
public class RemoteResourceConfig {
    @Bean
    @Qualifier("resource1")
    public OAuth2ProtectedResourceDetails resource1() {
        OAuth2ProtectedResourceDetails resourceDetails = new ClientCredentialsResourceDetails();
        return resourceDetails;
    }
}