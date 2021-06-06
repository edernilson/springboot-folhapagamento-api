package com.edernilson.folhapagamento.message;

import com.edernilson.folhapagamento.FolhaPagamentoApplication;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class FolhaMessageSender {

    private static final Logger log = LoggerFactory.getLogger(FolhaMessageSender.class);

    private final RabbitTemplate rabbitTemplate;

    public FolhaMessageSender(final RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void enviaEmailDaFolha(String email, String nomes) {
        final var messagem = new FolhaMessage(email, nomes);
        log.info("Enviando mensagem...");
        rabbitTemplate.convertAndSend(FolhaPagamentoApplication.EXCHANGE_NAME, FolhaPagamentoApplication.ROUTING_KEY, messagem);
    }
}