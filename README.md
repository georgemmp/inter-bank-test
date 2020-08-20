# Teste - JAVA API

[![Build Status](https://travis-ci.org/joemccann/dillinger.svg?branch=master)](https://travis-ci.org/joemccann/dillinger)

### DOCS
Para executar o projeto clique com o botão direito na classe princial 'TestInterBankApplication.java' -> Run As -> Spring Boot App.
Ou execute o comando 
```sh
./mvnw spring-boot:run
```
O projeto será executado na porta padrão 8080.
Para ver a documentação de todos os end-points acesse http://localhost:8080/swagger-ui.html

### Banco de dados
Para ter acesso o banco de dados http://localhost:8080/h2-console

 - Driver Class: org.h2.Driver
 - JDBC URL: jdbc:h2:mem:testdb
 - User Name: sa
 - Password:

### Testes Unitários

Para executar os testes vá até o pacote de testes e clique com o botão direito em uma das classes de testes, que estão nos pacotes repository e resource, Run As -> JUnit Test.
Ou se preferir rode os comandos maven.

