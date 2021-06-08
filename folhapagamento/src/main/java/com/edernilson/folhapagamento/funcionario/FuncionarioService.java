package com.edernilson.folhapagamento.funcionario;

import java.util.List;
import java.util.Optional;

import com.edernilson.folhapagamento.contacorrente.ContaCorrente;
import com.edernilson.folhapagamento.empresa.Empresa;
import com.edernilson.folhapagamento.empresa.EmpresaRepository;
import com.edernilson.folhapagamento.exception.BusinessException;

import org.springframework.stereotype.Service;

@Service
public class FuncionarioService {
    
    FuncionarioRepository repository;
    EmpresaRepository empresaRepository;

    public FuncionarioService(FuncionarioRepository repository, EmpresaRepository empresaRepository) {
        this.repository = repository;
        this.empresaRepository = empresaRepository;
    }

    public List<Funcionario> findAll() {
        List<Funcionario> items = repository.findAll();
        if (items == null || items.isEmpty()) {
            throw new BusinessException("01", "Nenhum funcionario encontrado");
        }
        return items;
    }

    public Funcionario findById(Long id) {
        Optional<Funcionario> existingItemOptional = repository.findById(id);
        if (existingItemOptional.isPresent()) {
            return existingItemOptional.get();
        }
        throw new BusinessException("01", "Funcionario não encontrado com id: "+id);
    }

    public Funcionario create(FuncionarioDTO payload) {
        Optional<Empresa> empresaOptional = empresaRepository.findById(payload.getCompanyId());
        if (!empresaOptional.isPresent()) {
            throw new BusinessException("01", "Empresa não encontrada com companyId: "+payload.getCompanyId());
        }
        ContaCorrente contaCorrente = new ContaCorrente(payload.getBalance());
        Funcionario savedFuncionario = repository.save(payload.toEntity(empresaOptional.get(), contaCorrente));

        return savedFuncionario;
    }

    public Funcionario update(Long id, FuncionarioDTO payload) {
        Optional<Empresa> empresaOptional = empresaRepository.findById(payload.getCompanyId());
        if (!empresaOptional.isPresent()) {
            throw new BusinessException("01", "Empresa não encontrada com companyId: "+payload.getCompanyId());
        }        
        Optional<Funcionario> funcionarioOptional = repository.findById(id);
        if (funcionarioOptional.isPresent()) {
            Funcionario funcionario = funcionarioOptional.get();
            funcionario.setName(payload.getName());
            funcionario.setSalary(payload.getSalary());
            funcionario.setEmpresa(empresaOptional.get());
            return repository.save(funcionario);
        }
        throw new BusinessException("01", "Funcionario não encontrado com id: "+id);
    }

    public void delete(Long id) {
         repository.deleteById(id);
    }

    public Double getSaldo(Long id) {
        Optional<Funcionario> funcionarioOptional = repository.findById(id);

        if (funcionarioOptional.isPresent()) {
            Funcionario funcionario = funcionarioOptional.get();
            return funcionario.obterSaldoContaCorrente();
        }
        throw new BusinessException("01", "Funcionario não encontrado com id: "+id);
    }
}
