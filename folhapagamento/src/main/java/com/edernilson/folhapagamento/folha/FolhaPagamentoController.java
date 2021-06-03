package com.edernilson.folhapagamento.folha;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/folhaPagamento")
public class FolhaPagamentoController {

    @Autowired
    FolhaPagamentoService folhaPagamentoService;

    @PostMapping("empresa/{id}/pagarSalarios")
    public ResponseEntity<?> create(@PathVariable("id") Long id) {
        try {
            folhaPagamentoService.pagarFolhaDaEmpresa(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }
}
