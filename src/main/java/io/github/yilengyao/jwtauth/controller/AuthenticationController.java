package io.github.yilengyao.jwtauth.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController {

    @GetMapping("/hello")
    public String hello() {
        return "Hello, World!";
    }
}

