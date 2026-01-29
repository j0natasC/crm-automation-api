package com.jonatas.rbacapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jonatas.rbacapi.domain.entity.Lead;
import com.jonatas.rbacapi.repository.LeadRepository;
import com.jonatas.rbacapi.service.LeadProducer;

@RestController
@RequestMapping("/leads")
public class LeadController {

    @Autowired
    private LeadRepository leadRepository;

    @Autowired
    private LeadProducer leadProducer;

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public Lead criar(@RequestBody Lead lead) {
        Lead leadSalvo = leadRepository.save(lead);

        leadProducer.enviarParaFila(leadSalvo);

        return leadSalvo;

    }

}
