package com.edernilson.folhapagamento.empresa;

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

@Api(tags = "Empresa", description = " ")
@RestController
@RequestMapping("/empresa")
class EmpresaController {

    EmpresaService empresaService;

    public EmpresaController(EmpresaService empresaService) {
        this.empresaService = empresaService;
    }

    @ApiOperation(value = "Lista de empresas cadastrados", response = EmpresaDTO.class)
    @GetMapping
    public ResponseEntity<List<EmpresaDTO>> getAll() {
        List<EmpresaDTO> items = empresaService.findAll();
        return new ResponseEntity<>(items, HttpStatus.OK);
    }

    @ApiOperation(value = "Procura por uma empresa pelo id", response = EmpresaDTO.class)
    @GetMapping("{id}")
    public ResponseEntity<EmpresaDTO> getById(@PathVariable("id") Long id) {
        EmpresaDTO existing = empresaService.findById(id);
        return new ResponseEntity<>(existing, HttpStatus.OK);
    }

    @ApiOperation(value = "Adiciona uma empresa", response = EmpresaDTO.class)
    @PostMapping
    public ResponseEntity<EmpresaDTO> create(@Valid @RequestBody EmpresaDTO payload, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            String erros = bindingResult.getFieldErrors().stream().map(e -> e.getDefaultMessage()).collect(Collectors.joining(", "));
            throw new BusinessException("10", erros);
        }
        EmpresaDTO saved = empresaService.create(payload);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    @ApiOperation(value = "Atualiza uma empresa", response = EmpresaDTO.class)
    @PutMapping("{id}")
    public ResponseEntity<EmpresaDTO> update(@PathVariable("id") Long id, @Valid @RequestBody EmpresaDTO payload, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            String erros = bindingResult.getFieldErrors().stream().map(e -> e.getDefaultMessage()).collect(Collectors.joining(", "));
            throw new BusinessException("10", erros);
        }        
        EmpresaDTO saved = empresaService.update(id, payload);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    @ApiOperation(value = "Exclui uma empresa")
    @DeleteMapping("{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") Long id) {
        empresaService.delete(id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @ApiOperation(tags = " ", value = "Obtem o saldo da conta corrente da empresa")
    @GetMapping("{id}/obterSaldo")
    public ResponseEntity<Double> getSaldo(@PathVariable("id") Long id) {
        Double saldo = empresaService.getSaldo(id);
        return new ResponseEntity<>(saldo, HttpStatus.OK);
    }
}