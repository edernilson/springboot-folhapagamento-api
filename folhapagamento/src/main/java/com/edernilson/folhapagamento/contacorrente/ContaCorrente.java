package com.edernilson.folhapagamento.contacorrente;

import java.util.Random;

import com.edernilson.folhapagamento.funcionario.Funcionario;

public class ContaCorrente { 
    
    static Random random = new Random();

    public static Double obterSaldo(Funcionario funcionario) {
        return random.nextDouble()*funcionario.getSalary();
    }
}
