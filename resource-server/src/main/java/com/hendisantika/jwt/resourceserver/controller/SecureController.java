package com.hendisantika.jwt.resourceserver.controller;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * Project : springboot-jwt-oauth2-example
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 2018-12-28
 * Time: 07:10
 * To change this template use File | Settings | File Templates.
 */
@RestController
public class SecureController {

    @RequestMapping(method = RequestMethod.GET, value = "/api/me")
    public Map<String, Object> me(Authentication authentication) {
        Map<String, Object> result = new HashMap<>();
        result.put("authentication", authentication);
        return result;
    }
}