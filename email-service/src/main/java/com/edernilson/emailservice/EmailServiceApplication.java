package com.edernilson.emailservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EmailServiceApplication {

	public static final String EXCHANGE_NAME = "folhapagamento.exchange";
	public static final String QUEUE_NAME = "folhapagamento.queue";
	public static final String ROUTING_KEY = "envio.email";

	public static void main(String[] args) {
		SpringApplication.run(EmailServiceApplication.class, args);
	}

}
