package com.edernilson.folhapagamento.funcionario;

import com.edernilson.folhapagamento.contacorrente.ContaCorrente;
import com.edernilson.folhapagamento.empresa.Empresa;

public class FuncionarioDTO {

    private Long id;
    private String name;
    private Double salary;
    private Double balance;
    private Long companyId;

    public FuncionarioDTO() {
    }

    public FuncionarioDTO(Long id, String name, Double salary, Double balance, Long companyId) {
        this.id = id;
        this.name = name;
        this.salary = salary;
        this.balance = balance;
        this.companyId = companyId;
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

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public Funcionario toEntity(Empresa empresa, ContaCorrente contaCorrente) {
        return new Funcionario(this.name, this.salary, contaCorrente, empresa);
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

}
