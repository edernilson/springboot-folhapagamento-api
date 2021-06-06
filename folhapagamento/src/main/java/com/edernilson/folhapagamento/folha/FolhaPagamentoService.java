package com.edernilson.folhapagamento.folha;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import com.edernilson.folhapagamento.contacorrente.ContaCorrenteService;
import com.edernilson.folhapagamento.empresa.Empresa;
import com.edernilson.folhapagamento.empresa.EmpresaRepository;
import com.edernilson.folhapagamento.funcionario.Funcionario;
import com.edernilson.folhapagamento.funcionario.FuncionarioRepository;
import com.edernilson.folhapagamento.message.FolhaMessageSender;

import org.springframework.stereotype.Service;

@Service
public class FolhaPagamentoService {

    private static final double PERCENTUAL_DESCONTO = 0.38D;

    EmpresaRepository empresaRepository;
    FuncionarioRepository funcionarioRepository;

    ContaCorrenteService contaCorrenteService;

    FolhaMessageSender folhaMessageSender;

    public FolhaPagamentoService(EmpresaRepository empresaRepository, FuncionarioRepository funcionarioRepository,
            ContaCorrenteService contaCorrenteService, FolhaMessageSender folhaMessageSender) {
        this.empresaRepository = empresaRepository;
        this.funcionarioRepository = funcionarioRepository;
        this.contaCorrenteService = contaCorrenteService;
        this.folhaMessageSender = folhaMessageSender;
    }

    /**
     * Paga o salario dos funcionarios da empresa informada
     * 
     * @param id ID da Empresa
     * @throws Exception
     */
    @Transactional
    public void pagarFolhaDaEmpresa(Long id) throws Exception {
        Optional<Empresa> empresaOptional = empresaRepository.findById(id);
        if (!empresaOptional.isPresent()) {
            throw new Exception();
        }
        Empresa empresa = empresaOptional.get();
        Optional<List<Funcionario>> listaFuncionarios = Optional.of(funcionarioRepository.findAllByEmpresa(empresa));

        if (!listaFuncionarios.isPresent()) {
            return;
        }

        double valorTotalFolha = listaFuncionarios.get().stream().map(f -> {
            try {
                contaCorrenteService.transferir(empresa.getContaCorrente(), f.getContaCorrente(), f.getSalary());
            } catch (Exception e) {
                throw new FolhaPagamentoException("Erro no pagamento do Funcionario: "+f.getName());
            }
            return f.getSalary();
        }).reduce(0D, Double::sum);

        double valorADebitar = valorTotalFolha * PERCENTUAL_DESCONTO / 100;
        contaCorrenteService.debitar(empresa.getContaCorrente(), valorADebitar);

        String nomes = listaFuncionarios.get().stream().map(f -> f.getName()).collect(Collectors.joining(", "));

        folhaMessageSender.enviaEmailDaFolha(nomes);
    }
}