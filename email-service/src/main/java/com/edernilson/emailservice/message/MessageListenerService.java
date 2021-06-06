package com.edernilson.emailservice.message;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import com.edernilson.emailservice.EmailServiceApplication;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
@ConfigurationProperties(prefix = "send.mail")
public class MessageListenerService {

    private static final Logger log = LoggerFactory.getLogger(MessageListenerService.class);

    JavaMailSender mailSender;

    String from;
    String subject;

    public MessageListenerService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @RabbitListener(queues = EmailServiceApplication.QUEUE_NAME)
    public void receiveMessage(final FolhaMessage folhaMessage) {
        System.out.println("Recebendo mensagem...");
        log.info("Recebendo mensagem: {}", folhaMessage.toString());

        try { 
            sendMail(folhaMessage);
        } catch (Exception e) {
            log.error("Erro ao enviar email para : {}", folhaMessage.getEmail());
        }
    }

    public void sendMail(FolhaMessage folhaMessage) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();

        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(message, true);//instantiates a multipart message

        mimeMessageHelper.setTo(folhaMessage.getEmail());
        mimeMessageHelper.setFrom(from);
        mimeMessageHelper.setSubject(subject);
        mimeMessageHelper.setText(folhaMessage.getFuncionarios());

        mailSender.send(message);
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    
    

}