package com.edernilson.folhapagamento.contacorrente;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContaCorrenteRepository extends CrudRepository<ContaCorrente, Long>{}