package com.edernilson.folhapagamento;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FolhaPagamentoApplication {

	public static final String EXCHANGE_NAME = "folhapagamento.exchange";
	public static final String ROUTING_KEY = "envio.email";

	public static void main(String[] args) {
		SpringApplication.run(FolhaPagamentoApplication.class, args);
	}

}
