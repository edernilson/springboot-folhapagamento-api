# Microservices - Folha Pagamentos

Tecnologias: Spring-boot, Java 11, PostgreSQL 11


## Serviço 1

Criar um serviço especializado em envio de email
    [ ] Criar Serviço
    [ ] Receber mensagem do RabbitMQ enviar email

## Serviço 2

Criar um serviço API REST utilizando as seguintes tecnologias: Java 11 + Spring 5 (Boot e Data) + PostgreSQL 11

    [X] Criar Serviço
    [ ] Cadastro de funcionário
    [ ] Obter saldo da conta corrente do funcionário
    [ ] Cadastro de empresa
    [ ] Obter saldo da conta corrente da empresa
    [ ] Pagamento de salário dos funcionários
    [ ] A API deverá ser documentada com o swagger

* Regra de negócio:

        [ ] Quando uma empresa paga o salário de um ou mais funcionários, deve ser descontada uma tarifa de 0.38% sobre o total da folha (R$)
        [ ] No serviço de pagamentos, implementar um método de autenticação via login e senha retornando um [token] de segurança, e as demais APIs devem obrigar o uso desse token
        [ ] Quando um pagamento de uma folha for executado, deverá ser enviado um email para a empresa pagante contendo a lista dos funcionários pagos
            Obs.: Essa funcionalidade deve ser um microserviço à parte (Serviço 1) para envio de email, o qual tratará mensagens assíncronas do RabbitMQ, enviadas previamente pelo serviço de pagamentos
            
* Diferencial

        Construir um container docker para cada serviço
        
        
        