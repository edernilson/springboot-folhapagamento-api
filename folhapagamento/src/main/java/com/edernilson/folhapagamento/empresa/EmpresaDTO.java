package com.edernilson.folhapagamento.empresa;

public class EmpresaDTO {

    private Long id;
    private String corporateName;
    private Double balance;

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

    public Empresa toEntity() {
        return new Empresa(this.corporateName, this.balance);
    }
    
}
