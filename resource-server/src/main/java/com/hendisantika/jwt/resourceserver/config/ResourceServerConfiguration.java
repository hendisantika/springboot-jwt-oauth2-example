package com.hendisantika.jwt.resourceserver.config;

import com.hendisantika.jwt.PublicJWTConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

/**
 * Created by IntelliJ IDEA.
 * Project : springboot-jwt-oauth2-example
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 2018-12-28
 * Time: 07:05
 * To change this template use File | Settings | File Templates.
 */
@Configuration
@EnableResourceServer
@Import(PublicJWTConfig.class)
public class ResourceServerConfiguration  extends ResourceServerConfigurerAdapter {


    @Autowired
    TokenStore tokenStore;

    @Autowired
    JwtAccessTokenConverter tokenConverter;

    @Override
    public void configure(HttpSecurity http) throws Exception {
        super.configure(http);
        // @formatter:off
        http
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.NEVER))
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(HttpMethod.OPTIONS, "/api/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/**").hasAuthority("SCOPE_read")
                        .requestMatchers(HttpMethod.PATCH, "/api/**").hasAuthority("SCOPE_write")
                        .requestMatchers(HttpMethod.POST, "/api/**").hasAuthority("SCOPE_write")
                        .requestMatchers(HttpMethod.PUT, "/api/**").hasAuthority("SCOPE_write")
                        .requestMatchers(HttpMethod.DELETE, "/api/**").hasAuthority("SCOPE_write")
                        .requestMatchers("/admin/**").hasRole("ADMIN")
                        .anyRequest().authenticated()
                );

        // @formatter:on
    }

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        System.out.println("Configuring ResourceServerSecurityConfigurer ");
        resources.resourceId("dummy").tokenStore(tokenStore);
    }




}
