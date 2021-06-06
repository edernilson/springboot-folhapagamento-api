package com.edernilson.emailservice.emailservice;

import javax.mail.MessagingException;

import com.edernilson.emailservice.message.FolhaMessage;
import com.edernilson.emailservice.message.MessageListenerService;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SendMailTest {
    
    @Autowired
    MessageListenerService messageListenerService;

    @Test
    void testReceiveMessage() throws MessagingException {
        FolhaMessage folhaMessage = new FolhaMessage("somapay@somapay.com", "Eder Nilson");
        messageListenerService.sendMail(folhaMessage);
    }
}
