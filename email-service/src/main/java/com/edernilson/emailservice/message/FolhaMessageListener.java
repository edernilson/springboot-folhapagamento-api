package com.edernilson.emailservice.message;

import com.edernilson.emailservice.EmailServiceApplication;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class FolhaMessageListener {
    private static final Logger log = LoggerFactory.getLogger(FolhaMessageListener.class);

    @RabbitListener(queues = EmailServiceApplication.QUEUE_NAME)
    public void receiveMessage(final FolhaMessage message) {
        System.out.println("Recebendo mensagem...");
        log.info("Recebendo mensagem: {}", message.toString());
    }

}