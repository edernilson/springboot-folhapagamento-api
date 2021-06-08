package com.edernilson.folhapagamento.empresa;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.edernilson.folhapagamento.contacorrente.ContaCorrente;

public class EmpresaDTO {

    private Long id;
    @NotNull(message = "O nome  é obrigatório")
    private String corporateName;
    @NotNull(message = "Saldo bancário inicial é obrigatório")
    @Min(value = 0, message = "Valor do saldo bancário deve ser maior ou igual a zero")
    private Double balance;
    @NotNull(message = "O email é obrigatório")
    private String email;

    public EmpresaDTO() {
    }

    public EmpresaDTO(Long id, String corporateName, Double balance) {
        this.id = id;
        this.corporateName = corporateName;
        this.balance = balance;
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

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Empresa toEntity(ContaCorrente contaCorrente) {
        return new Empresa(this.corporateName, this.email, contaCorrente);
    }

}
