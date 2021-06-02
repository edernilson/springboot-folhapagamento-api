package com.edernilson.folhapagamento.empresa;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/empresa")
class EmpresaController {

    @Autowired
    EmpresaRepository repository;

    @GetMapping
    public ResponseEntity<List<Empresa>> getAll() {
        try {
            List<Empresa> items = new ArrayList<Empresa>();
            repository.findAll().forEach(items::add);
            if (items.isEmpty())
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);

            return new ResponseEntity<>(items, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("{id}")
    public ResponseEntity<Empresa> getById(@PathVariable("id") Long id) {
        Optional<Empresa> existingItemOptional = repository.findById(id);

        if (existingItemOptional.isPresent()) {
            return new ResponseEntity<>(existingItemOptional.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Empresa> create(@RequestBody EmpresaDTO payload) {
        try {
            Empresa savedEmpresa = repository.save(payload.toEntity());
            return new ResponseEntity<>(savedEmpresa, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
        }
    }

    @PutMapping("{id}")
    public ResponseEntity<Empresa> update(@PathVariable("id") Long id, @RequestBody Empresa payload) {
        Optional<Empresa> empresaOptional = repository.findById(id);
        if (empresaOptional.isPresent()) {
            Empresa empresa = empresaOptional.get();
            empresa.setCorporateName(payload.getCorporateName());
            return new ResponseEntity<>(repository.save(empresa), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") Long id) {
        try {
            repository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }

    @GetMapping("{id}/obterSaldo")
    public ResponseEntity<Double> getSaldo(@PathVariable("id") Long id) {
        Optional<Empresa> empresaOptional = repository.findById(id);

        if (empresaOptional.isPresent()) {
            Empresa empresa = empresaOptional.get();
            
            return new ResponseEntity<>(empresa.obterSaldoContaCorrente(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}