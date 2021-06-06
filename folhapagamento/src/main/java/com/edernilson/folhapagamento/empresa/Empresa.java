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

import org.hibernate.annotations.NotFound;

@Entity
public class Empresa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "corporate_name", length = 80)
    @NotFound
    private String corporateName;

    @JsonIgnore
    @OneToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "conta_corrente_id")
    @NotFound
    private ContaCorrente contaCorrente;

    @Column(length = 100)
    @NotFound
    private String email;

    public Empresa() {
    }

    public Empresa(String corporateName, String email, ContaCorrente contaCorrente) {
        this.corporateName = corporateName;
        this.email = email;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}