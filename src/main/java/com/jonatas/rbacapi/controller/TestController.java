package com.jonatas.rbacapi.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/api/test")
public class TestController {

    @GetMapping("/public")
    public String publicEndpoint() {
        return "Endpoind público está funcionando";
    }

    @GetMapping("/admin")
    public String adminEndpoint() {
        return "Endpoint ADMIN está ok";
    }

}
