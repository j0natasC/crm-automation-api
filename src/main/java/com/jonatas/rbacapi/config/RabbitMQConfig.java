
package com.jonatas.rbacapi.config;

import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    @Value("${crm.queue.leads}")
    private String queueName;

    @Bean
    public Queue queue() {
        return new Queue(queueName, true);
    }
}