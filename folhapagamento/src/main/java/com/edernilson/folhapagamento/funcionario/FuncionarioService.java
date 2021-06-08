package com.edernilson.folhapagamento.funcionario;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.edernilson.folhapagamento.contacorrente.ContaCorrente;
import com.edernilson.folhapagamento.empresa.Empresa;
import com.edernilson.folhapagamento.empresa.EmpresaRepository;
import com.edernilson.folhapagamento.exception.BusinessException;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class FuncionarioService {
    
    ModelMapper modelMapper;

    FuncionarioRepository repository;
    EmpresaRepository empresaRepository;

    public FuncionarioService(FuncionarioRepository repository, EmpresaRepository empresaRepository, ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
        this.repository = repository;
        this.empresaRepository = empresaRepository;
    }

    public List<FuncionarioDTO> findAll() {
        List<Funcionario> items = repository.findAll();
        if (items == null || items.isEmpty()) {
            throw new BusinessException("01", "Nenhum funcionario encontrado");
        }
        return items.stream()
                    .map(this::convertToDto)
                    .collect(Collectors.toList());
    }

    public FuncionarioDTO findById(Long id) {
        Optional<Funcionario> existingItemOptional = repository.findById(id);
        if (existingItemOptional.isPresent()) {
            return convertToDto(existingItemOptional.get());
        }
        throw new BusinessException("01", "Funcionario não encontrado com id: "+id);
    }

    public FuncionarioDTO create(FuncionarioDTO payload) {
        Optional<Empresa> empresaOptional = empresaRepository.findById(payload.getCompanyId());
        if (!empresaOptional.isPresent()) {
            throw new BusinessException("01", "Empresa não encontrada com companyId: "+payload.getCompanyId());
        }
        ContaCorrente contaCorrente = new ContaCorrente(payload.getBalance());
        Funcionario savedFuncionario = repository.save(payload.toEntity(empresaOptional.get(), contaCorrente));

        return convertToDto(savedFuncionario);
    }

    public FuncionarioDTO update(Long id, FuncionarioDTO payload) {
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
            return convertToDto(repository.save(funcionario));
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

    private FuncionarioDTO convertToDto(Funcionario funcionario) {
        FuncionarioDTO funcionarioDTO = modelMapper.map(funcionario, FuncionarioDTO.class);
        funcionarioDTO.setCompanyId(funcionario.getEmpresa().getId());
        funcionarioDTO.setBalance(funcionario.obterSaldoContaCorrente());
        return funcionarioDTO;
    }
}
