# Microservices - Folha Pagamentos

Tecnologias: Spring-boot, Java 11, PostgreSQL 11, RabbitMQ, Mailhog, Swagger, Docker e ModelMapper

## Arquitetura
![Arquitetura](arquitetura.png?raw=true "Arquitetura do teste")

## Serviço 1

Criar um serviço especializado em envio de email

    [X] Criar Serviço
    [X] Receber mensagem do RabbitMQ
    [X] Enviar email para empresa com a lista de funcionários pagos

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
    [X] A API deve ser segura utilizando Token

* Regra de negócio:

        [X] Quando uma empresa paga o salário de um ou mais funcionários, deve ser descontada uma tarifa de 0.38% sobre o total da folha (R$)
        [X] No serviço de pagamentos, implementar um método de autenticação via login e senha retornando um [token] de segurança, e as demais APIs devem obrigar o uso desse token
        [X] Quando um pagamento de uma folha for executado, deverá ser enviado um email para a empresa pagante contendo a lista dos funcionários pagos
            Obs.: Essa funcionalidade deve ser um microserviço à parte (Serviço 1) para envio de email, o qual tratará mensagens assíncronas do RabbitMQ, enviadas previamente pelo serviço de pagamentos
            
* Diferencial

    Construir um container docker para cada serviço
        [X] Serviço de email
        [X] Serviço de API REST
        

## Como Compilar

1. Serviço de Folha Pagamento

        mvn spring-boot:build-image -DskipTests -Dspring-boot.build-image.imageName=edernilson/folha-pagamento-api:0.0.2

2. Serviço de Email

        mvn spring-boot:build-image -DskipTests -Dspring-boot.build-image.imageName=edernilson/folha-pagamento-email-service:0.0.1
## Como Executar

1. Executar com o docker compose

        docker-compose up -d

2. Se estiver no linux, executar o script para abrir os gerenciadores e documentação Swagger

        chmod +x runAll.sh  && ./runAll.sh

3. Abrir o navegador os gerenciadores:

    * RabbitMQ
        * http://localhost:15672
        * user: guest
        * password: 12345678
    * PgAdmin 4
        * http://localhost:5000
        * user: guest@guest.com
        * password: 12345678
    * Mailhog 
        * http://localhost:8025
    * Swagger API Docs
        * http://localhost:8080/api/swagger-ui.html

4. Como obter o token para conectar no Swagger

        curl -X POST 'localhost:8080/api/auth' --header 'Content-Type: application/json' --data '{  "username": "user", "password": "12345678"}'

## License

MIT