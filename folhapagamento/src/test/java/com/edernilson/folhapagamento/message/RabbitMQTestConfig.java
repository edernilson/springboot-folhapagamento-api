package com.edernilson.folhapagamento.message;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.test.RabbitListenerTest;
import org.springframework.context.annotation.Configuration;

@Configuration
@RabbitListenerTest
public class RabbitMQTestConfig {
    public static final String QUEUE_NAME = "folhapagamento.queue";

    @RabbitListener(queues = QUEUE_NAME)
    public void receiveMessage(final Message message) {
        System.out.println(String.format("Recebendo mensagem: {}", message.toString()));
    }
}
