package com.hendisantika.jwt.aggregationserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;

/**
 * Created by IntelliJ IDEA.
 * Project : springboot-jwt-oauth2-example
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 2018-12-28
 * Time: 06:48
 * To change this template use File | Settings | File Templates.
 */

@Configuration
@ComponentScan
@EnableAutoConfiguration
@EnableOAuth2Client
public class AggregationServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(AggregationServerApplication.class, args);
    }
}
