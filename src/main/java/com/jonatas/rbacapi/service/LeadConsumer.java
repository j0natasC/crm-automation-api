package com.jonatas.rbacapi.service;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class LeadConsumer {

    @RabbitListener(queues = "${crm.queue.leads}")
    public void consumir(String mensagem) {
        System.out.println("--------------------------------------------");
        System.out.println(">>> CONSUMIDOR ATIVO: Processando novo Lead...");
        System.out.println(">>> Dados recebidos: " + mensagem);
        System.out.println(">>> Ação: Simulando envio de e-mail de boas-vindas...");
        System.out.println("--------------------------------------------");
    }
}