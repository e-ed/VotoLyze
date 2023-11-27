
# VotoLyze
Backend do projeto VotoLyze - Disciplina de Engenharia de Software 2 - IFSP Campus São João da Boa Vista

# Requisitos
Java 17, Maven (se for buildar o projeto), MySQL

# Instruções
Antes de iniciar o servidor, criar uma database chamada VOTOLYZE:

```create database VOTOLYZE;```


Para executar a aplicação, você pode: 
- abrir o projeto em uma IDE, fazer a build e rodar a função main (VotoLyzeApplication.java);
- buildar você mesmo pelo terminal com o Maven



## Abrir em uma IDE
Baixar o código do projeto (clicar em "<> Code" e baixar como ZIP), abrir o projeto no NetBeans/IntelliJ e fazer a build. Executar a função main do projeto, da classe VotoLyzeApplication.java. 


## Build pelo terminal
Abrir a pasta do projeto no terminal e executar os comandos: 

```mvn clean```

```mvn install```

```mvn spring-boot:run```

![alt text](https://i.imgur.com/GYWd5ev.png)

# Outras informações

Ao rodar o projeto, todas as tabelas serão criadas e serão feitas algumas inserções na tabela de Partidos.

Caso você reinicie o servidor, todos os cadastros serão removidos.

Entrando no endereço http://localhost:8080/ você será redirecionado para a página inicial.
