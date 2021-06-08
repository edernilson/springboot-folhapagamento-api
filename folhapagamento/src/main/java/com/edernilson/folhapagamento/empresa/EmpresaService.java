package com.edernilson.folhapagamento.empresa;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.edernilson.folhapagamento.contacorrente.ContaCorrente;
import com.edernilson.folhapagamento.exception.BusinessException;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class EmpresaService {
    
    ModelMapper modelMapper;
    EmpresaRepository repository;

    public EmpresaService(EmpresaRepository repository, ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
        this.repository = repository;
    }

    public List<EmpresaDTO> findAll() {
        List<Empresa> items = repository.findAll();
        if (items == null || items.isEmpty()) {
            throw new BusinessException("01", "Nenhuma empresa encontrada");
        }
        return items.stream()
                    .map(this::convertToDto)
                    .collect(Collectors.toList());
    }

    public EmpresaDTO findById(Long id) {
        Optional<Empresa> existing = repository.findById(id);
        if (existing.isPresent()) {
            return convertToDto(existing.get());
        }
        throw new BusinessException("01", "Empresa não encontrada com id: "+id);
    }

    public EmpresaDTO create(EmpresaDTO payload) {
        ContaCorrente contaCorrente = new ContaCorrente(payload.getBalance());
        Empresa savedEmpresa = repository.save(payload.toEntity(contaCorrente));

        return convertToDto(savedEmpresa);
    }

    public EmpresaDTO update(Long id, EmpresaDTO payload) {
        Optional<Empresa> existing = repository.findById(id);
        if (existing.isPresent()) {
            Empresa empresa = existing.get();
            empresa.setCorporateName(payload.getCorporateName());
            return convertToDto(repository.save(empresa));
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

    private EmpresaDTO convertToDto(Empresa empresa) {
        EmpresaDTO empresaDTO = modelMapper.map(empresa, EmpresaDTO.class);
        empresaDTO.setBalance(empresa.obterSaldoContaCorrente());
        return empresaDTO;
    }
}
