package com.jonatas.rbacapi.service;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.jonatas.rbacapi.domain.entity.Lead;

@Service
public class LeadProducer {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Value("${crm.queue.leads}")
    private String queue;

    public void enviarParaFila(Lead lead) {
        String mensagem = "Novo Lead: " + lead.getNome() + " | Email: " + lead.getEmail();
        rabbitTemplate.convertAndSend(queue, mensagem);
        System.out.println(">>> Mensagem enviada para a fila RabbitMQ!");
    }
}