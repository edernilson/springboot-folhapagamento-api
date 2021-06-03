package com.edernilson.folhapagamento.regras;

public interface PagamentoStrategy {

    Double calcular(Double salary, Double amountTotalPayroll);
    
}
