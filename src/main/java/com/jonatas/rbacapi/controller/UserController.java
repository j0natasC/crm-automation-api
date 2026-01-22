package com.jonatas.rbacapi.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @GetMapping
    @PreAuthorize("hasAuthority('READ_USER')")
    public String listUsers() {
        return "Listando usuários";
    }

    @PostMapping
    @PreAuthorize("hasAuthority('CREATE_USER')")
    public String createUser() {
        return "Usuário criado";
    }
}
