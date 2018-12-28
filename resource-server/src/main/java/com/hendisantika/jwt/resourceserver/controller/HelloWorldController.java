package com.hendisantika.jwt.resourceserver.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by IntelliJ IDEA.
 * Project : springboot-jwt-oauth2-example
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 2018-12-28
 * Time: 07:09
 * To change this template use File | Settings | File Templates.
 */
@RestController
public class HelloWorldController {
    @RequestMapping("/helloworld")
    public String home() {
        return "Hello world !";
    }
}