# Microservices - Folha Pagamentos

Tecnologias: Spring-boot, Java 11, PostgreSQL 11, RabbitMQ, Mailhog e Swagger


## Serviço 1

Criar um serviço especializado em envio de email

    [X] Criar Serviço
    [X] Receber mensagem do RabbitMQ
    [X] Enviar email para empresa

## Serviço 2

Criar um serviço API REST utilizando as seguintes tecnologias: Java 11 + Spring 5 (Boot e Data) + PostgreSQL 11

    [X] Criar Serviço
    [X] Cadastro de funcionário
    [X] Obter saldo da conta corrente do funcionário
    [X] Cadastro de empresa
    [X] Obter saldo da conta corrente da empresa
    [X] Pagamento de salário dos funcionários
    [X] A API deverá ser documentada com o swagger
    [X] Enviar mensagem para o RabbitMQ com email da empresa e lista de funcionarios
    [ ] A API deve ser segura utilizando Token

* Regra de negócio:

        [X] Quando uma empresa paga o salário de um ou mais funcionários, deve ser descontada uma tarifa de 0.38% sobre o total da folha (R$)
        [ ] No serviço de pagamentos, implementar um método de autenticação via login e senha retornando um [token] de segurança, e as demais APIs devem obrigar o uso desse token
        [X] Quando um pagamento de uma folha for executado, deverá ser enviado um email para a empresa pagante contendo a lista dos funcionários pagos
            Obs.: Essa funcionalidade deve ser um microserviço à parte (Serviço 1) para envio de email, o qual tratará mensagens assíncronas do RabbitMQ, enviadas previamente pelo serviço de pagamentos
            
* Diferencial

    Construir um container docker para cada serviço
        [ ] Serviço de email
        [ ] Serviço de API REST
        
                
## Como Executar

1. Se estiver no linux, executar o script para abrir os gerenciadores e executar os 2 serviços

        ./runAll.sh

2. Executar com o docker composer

        docker-compose up

3. Abrir o navegador os gerenciadores:

    * RabbitMQ
        * http://localhost:15672
        * user: guest
        * password: 12345678
    * PgAdmin 4
        * http://localhost:5000
        * user: guest
        * password: 12345678
    * Mailhog 
        * http://localhost:8025
    * Swagger API Docs
        * http://localhost:8080/api-docs
