package com.edernilson.folhapagamento.funcionario;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.edernilson.folhapagamento.contacorrente.ContaCorrente;
import com.edernilson.folhapagamento.empresa.Empresa;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Funcionario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 60)
    private String name;

    private Double salary;

    @JsonIgnore
    @OneToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "conta_corrente_id")
    private ContaCorrente contaCorrente;

    @ManyToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "empresa_id")
    private Empresa empresa;

    public Funcionario() {
    }

    public Funcionario(String name, Double salary, ContaCorrente contaCorrente, Empresa empresa) {
        this.name = name;
        this.salary = salary;
        this.contaCorrente = contaCorrente;
        this.empresa = empresa;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
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

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

}