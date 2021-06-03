package com.edernilson.folhapagamento.contacorrente;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

@Service
public class ContaCorrenteService {
    
    ContaCorrenteRepository repository;

    public ContaCorrenteService(ContaCorrenteRepository repository) {
        this.repository = repository;
    }
   
    @Transactional
    public void debitar(ContaCorrente contaCorrente, Double valor) throws Exception {

        Double saldo = contaCorrente.getBalance();
        if (saldo < valor) {
            throw new Exception("Sem saldo");
        }

        contaCorrente.setBalance(saldo - valor);
        repository.save(contaCorrente);
    }

    @Transactional
    public void creditar(ContaCorrente contaCorrente, Double valor) {

        Double saldo = contaCorrente.getBalance();

        contaCorrente.setBalance(saldo + valor);
        repository.save(contaCorrente);
    }

    public void transferir(ContaCorrente debito, ContaCorrente credito, Double valor) throws Exception {
        debitar(debito, valor);
        creditar(credito, valor);
    }    

}
