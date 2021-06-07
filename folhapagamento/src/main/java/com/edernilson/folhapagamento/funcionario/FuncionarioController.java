package com.edernilson.folhapagamento.funcionario;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.edernilson.folhapagamento.contacorrente.ContaCorrente;
import com.edernilson.folhapagamento.empresa.Empresa;
import com.edernilson.folhapagamento.empresa.EmpresaRepository;

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

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "Funcionario", description = " ")
@RestController
@RequestMapping("/funcionario")
class FuncionarioController {

    FuncionarioRepository repository;
    EmpresaRepository empresaRepository;

    public FuncionarioController(FuncionarioRepository repository, EmpresaRepository empresaRepository) {
        this.repository = repository;
        this.empresaRepository = empresaRepository;
    }

    @GetMapping
    public ResponseEntity<List<Funcionario>> getAll() {
        System.out.println("Teste");
        try {
            List<Funcionario> items = new ArrayList<Funcionario>();

            repository.findAll().forEach(items::add);

            if (items.isEmpty())
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);

            return new ResponseEntity<>(items, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("{id}")
    public ResponseEntity<Funcionario> getById(@PathVariable("id") Long id) {
        Optional<Funcionario> existingItemOptional = repository.findById(id);

        if (existingItemOptional.isPresent()) {
            return new ResponseEntity<>(existingItemOptional.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Funcionario> create(@RequestBody FuncionarioDTO payload) {
        try {

            Optional<Empresa> empresaOptional = empresaRepository.findById(payload.getCompanyId());
            if (!empresaOptional.isPresent()) {
                System.out.println("Post: " + payload);
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            }
            ContaCorrente contaCorrente = new ContaCorrente(payload.getBalance());
            Funcionario savedFuncionario = repository.save(payload.toEntity(empresaOptional.get(), contaCorrente));
            System.out.println("savedFuncionario" + savedFuncionario);

            return new ResponseEntity<>(savedFuncionario, HttpStatus.CREATED);
        } catch (Exception e) {
            System.out.println(e);
            return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
        }
    }

    @PutMapping("{id}")
    public ResponseEntity<Funcionario> update(@PathVariable("id") Long id, @RequestBody Funcionario payload) {
        Optional<Funcionario> funcionarioOptional = repository.findById(id);
        if (funcionarioOptional.isPresent()) {
            Funcionario funcionario = funcionarioOptional.get();
            funcionario.setName(payload.getName());
            funcionario.setSalary(payload.getSalary());
            return new ResponseEntity<>(repository.save(funcionario), HttpStatus.OK);
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

    @ApiOperation(tags = " ", value = "Obtem o saldo da conta corrente do funcionario")
    @GetMapping("{id}/obterSaldo")
    public ResponseEntity<Double> getSaldo(@PathVariable("id") Long id) {
        Optional<Funcionario> funcionarioOptional = repository.findById(id);

        if (funcionarioOptional.isPresent()) {
            Funcionario funcionario = funcionarioOptional.get();
            
            return new ResponseEntity<>(funcionario.obterSaldoContaCorrente(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
}