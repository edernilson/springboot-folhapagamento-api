package com.edernilson.folhapagamento.message;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class FolhaMessageSenderTest {

    @Autowired
    FolhaMessageSender folhaMessageSender;

    @Test
    void sendMessageTest() {
        String email = "somapay@somapay.com";
        String nomes = "Eder Nilson, William";
        folhaMessageSender.enviaEmailDaFolha(email, nomes);
    }
}
