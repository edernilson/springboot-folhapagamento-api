package com.edernilson.folhapagamento.funcionario;

public class FuncionarioDTO {

    private Long id;
    private String name;
    private Double salary;
    private Double balance;

    public FuncionarioDTO() {
    }

    public FuncionarioDTO(Long id, String name, Double salary, Double balance) {
        this.id = id;
        this.name = name;
        this.salary = salary;
        this.balance = balance;
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

    public Funcionario toEntity() {
        return new Funcionario(this.name, this.salary, this.balance);
    }

}
