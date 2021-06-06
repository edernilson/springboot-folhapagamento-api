package com.edernilson.emailservice.message;

import java.io.Serializable;

public class FolhaMessage implements Serializable {

    private String email;
    private String funcionarios;

    public FolhaMessage() {
    }

    public FolhaMessage(String email, String funcionarios) {
        this.email = email;
        this.funcionarios = funcionarios;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFuncionarios() {
        return funcionarios;
    }

    public void setFuncionarios(String funcionarios) {
        this.funcionarios = funcionarios;
    }

    @Override
    public String toString() {
        return "FolhaMessage [email=" + email + ", funcionarios=" + funcionarios + "]";
    }

}