package com.edernilson.folhapagamento.funcionario;

import java.util.List;

import com.edernilson.folhapagamento.empresa.Empresa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FuncionarioRepository extends JpaRepository<Funcionario, Long>{

    List<Funcionario> findAllByEmpresa(Empresa empresa);}