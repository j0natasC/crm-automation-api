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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/leads")
@Tag(name = "Leads", description = "Gestão de potenciais clientes e automação de CRM") //
public class LeadController {

    @Autowired
    private LeadRepository leadRepository;

    @Autowired
    private LeadProducer leadProducer;

    @Operation(summary = "Cadastrar novo Lead", description = "Salva o lead no banco de dados e dispara uma mensagem para a fila do RabbitMQ") //
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Lead criado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos enviados no corpo da requisição"),
            @ApiResponse(responseCode = "403", description = "Acesso negado (Requer perfil ADMIN)")
    }) //
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public Lead criar(@Valid @RequestBody Lead lead) {
        Lead leadSalvo = leadRepository.save(lead);

        leadProducer.enviarParaFila(leadSalvo);

        return leadSalvo;
    }
}