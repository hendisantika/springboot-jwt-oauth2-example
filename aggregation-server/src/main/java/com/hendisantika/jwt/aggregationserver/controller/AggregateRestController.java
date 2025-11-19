package com.hendisantika.jwt.aggregationserver.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * Project : springboot-jwt-oauth2-example
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 2018-12-28
 * Time: 06:54
 * To change this template use File | Settings | File Templates.
 */
@RestController
public class AggregateRestController extends AbstractTokenReusageRemoteRestController {

    @Autowired
    private OAuth2ProtectedResourceDetails resource1;

    @Value("${resource1.baseURL:http://localhost:8088}")
    private String resource1BaseURL;

    @RequestMapping(method = RequestMethod.GET, value = "/api/me")
    public Map me() {
        return remoteOAuthTokenReusageRestTemplate(resource1).getForEntity(resource1BaseURL + "/api/me", Map.class).getBody();
    }


}