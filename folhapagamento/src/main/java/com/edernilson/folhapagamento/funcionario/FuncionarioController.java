package com.edernilson.folhapagamento.funcionario;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import com.edernilson.folhapagamento.exception.BusinessException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "Funcionario", description = " ")
@RestController
@RequestMapping("/funcionario")
class FuncionarioController {

    FuncionarioService funcionarioService;

    public FuncionarioController(FuncionarioService funcionarioService) {
        this.funcionarioService = funcionarioService;
    }

    @ApiOperation(value = "Lista de funcionarios cadastrados", response = FuncionarioDTO.class)
    @GetMapping
    public ResponseEntity<List<FuncionarioDTO>> getAll() throws Exception {
        List<FuncionarioDTO> items = funcionarioService.findAll();
        return new ResponseEntity<>(items, HttpStatus.OK);
    }

    @ApiOperation(value = "Procura por um funcionario pelo id", response = FuncionarioDTO.class)
    @GetMapping("{id}")
    public ResponseEntity<FuncionarioDTO> getById(@PathVariable("id") Long id) {
        FuncionarioDTO funcionario = funcionarioService.findById(id);
        return new ResponseEntity<>(funcionario, HttpStatus.OK);
    }

    @ApiOperation(value = "Adiciona um funcionario", response = FuncionarioDTO.class)
    @PostMapping
    public ResponseEntity<FuncionarioDTO> create(@Valid @RequestBody FuncionarioDTO payload, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            String erros = bindingResult.getFieldErrors().stream().map(e -> e.getDefaultMessage()).collect(Collectors.joining(", "));
            throw new BusinessException("10", erros);
        }
        FuncionarioDTO savedFuncionario = funcionarioService.create(payload);
        return new ResponseEntity<>(savedFuncionario, HttpStatus.CREATED);
    }

    @ApiOperation(value = "Atualiza um funcionario", response = FuncionarioDTO.class)
    @PutMapping("{id}")
    public ResponseEntity<FuncionarioDTO> update(@PathVariable("id") Long id, @Valid @RequestBody FuncionarioDTO payload, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            String erros = bindingResult.getFieldErrors().stream().map(e -> e.getDefaultMessage()).collect(Collectors.joining(", "));
            throw new BusinessException("10", erros);
        }
        FuncionarioDTO savedFuncionario = funcionarioService.update(id, payload);
        return new ResponseEntity<>(savedFuncionario, HttpStatus.CREATED);
    }

    @ApiOperation(value = "Exclui um funcionario")
    @DeleteMapping("{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") Long id) {
        funcionarioService.delete(id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @ApiOperation(tags = " ", value = "Obtem o saldo da conta corrente do funcionario")
    @GetMapping("{id}/obterSaldo")
    public ResponseEntity<Double> getSaldo(@PathVariable("id") Long id) {
        Double saldo = funcionarioService.getSaldo(id);
        return new ResponseEntity<>(saldo, HttpStatus.OK);
    }
    
}