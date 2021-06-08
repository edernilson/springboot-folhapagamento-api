package com.edernilson.folhapagamento.empresa;

import java.util.List;
import java.util.Optional;

import com.edernilson.folhapagamento.contacorrente.ContaCorrente;
import com.edernilson.folhapagamento.exception.BusinessException;

import org.springframework.stereotype.Service;

@Service
public class EmpresaService {
    
    EmpresaRepository repository;

    public EmpresaService(EmpresaRepository repository) {
        this.repository = repository;
    }

    public List<Empresa> findAll() {
        List<Empresa> items = repository.findAll();
        if (items == null || items.isEmpty()) {
            throw new BusinessException("01", "Nenhuma empresa encontrada");
        }
        return items;
    }

    public Empresa findById(Long id) {
        Optional<Empresa> existing = repository.findById(id);
        if (existing.isPresent()) {
            return existing.get();
        }
        throw new BusinessException("01", "Empresa não encontrada com id: "+id);
    }

    public Empresa create(EmpresaDTO payload) {
        ContaCorrente contaCorrente = new ContaCorrente(payload.getBalance());
        Empresa savedEmpresa = repository.save(payload.toEntity(contaCorrente));

        return savedEmpresa;
    }

    public Empresa update(Long id, EmpresaDTO payload) {
        Optional<Empresa> existing = repository.findById(id);
        if (existing.isPresent()) {
            Empresa empresa = existing.get();
            empresa.setCorporateName(payload.getCorporateName());
            return repository.save(empresa);
        }
        throw new BusinessException("01", "Empresa não encontrada com id: "+id);
    }

    public void delete(Long id) {
         repository.deleteById(id);
    }

    public Double getSaldo(Long id) {
        Optional<Empresa> existing = repository.findById(id);

        if (existing.isPresent()) {
            Empresa empresa = existing.get();
            return empresa.obterSaldoContaCorrente();
        }
        throw new BusinessException("01", "Empresa não encontrada com id: "+id);
    }
}
