package com.edernilson.folhapagamento.empresa;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.edernilson.folhapagamento.contacorrente.ContaCorrente;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Empresa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "corporate_name", length = 80)
    private String corporateName;

    @JsonIgnore
    @OneToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "conta_corrente_id")
    private ContaCorrente contaCorrente;

    public Empresa() {
    }

    public Empresa(String corporateName, ContaCorrente contaCorrente) {
        this.corporateName = corporateName;
        this.contaCorrente = contaCorrente;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCorporateName() {
        return corporateName;
    }

    public void setCorporateName(String corporateName) {
        this.corporateName = corporateName;
    }

    public ContaCorrente getContaCorrente() {
        return contaCorrente;
    }

    public void setContaCorrente(ContaCorrente contaCorrente) {
        this.contaCorrente = contaCorrente;
    }
    
    public Double obterSaldoContaCorrente() {
        return this.contaCorrente.getBalance();
    }

}