package com.jonatas.rbacapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jonatas.rbacapi.domain.entity.Lead;
import com.jonatas.rbacapi.repository.LeadRepository;

@RestController
@RequestMapping("/leads")
public class LeadController {

    @Autowired
    private LeadRepository leadRepository;

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public Lead criar(@RequestBody Lead lead) {
        return leadRepository.save(lead);
    }

}
