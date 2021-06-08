package com.edernilson.folhapagamento.folha;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "Folha de Pagamento", description = " ")
@RestController
@RequestMapping("/folhaPagamento")
public class FolhaPagamentoController {

    FolhaPagamentoService folhaPagamentoService;

    public FolhaPagamentoController(FolhaPagamentoService folhaPagamentoService) {
        this.folhaPagamentoService = folhaPagamentoService;
    }

    @ApiOperation(value = "Pagar Salarios dos Funcionarios da Empresa informada ")
    @PostMapping("empresa/{id}/pagarSalarios")
    public ResponseEntity<?> pagarSalarios(@PathVariable("id") Long id) {
        try {
            folhaPagamentoService.pagarFolhaDaEmpresa(id);
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        } catch (FolhaPagamentoException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.EXPECTATION_FAILED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }
}
