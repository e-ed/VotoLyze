
# VotoLyze
Backend do projeto VotoLyze - Disciplina de Engenharia de Software 2 - IFSP Campus São João da Boa Vista

# Requisitos
Java 17, Maven (se for buildar o projeto), MySQL

# Instruções
Antes de iniciar o servidor, criar uma database chamada VOTOLYZE:

```create database VOTOLYZE;```


Para executar a aplicação, você pode: 
- executar o JAR do projeto (mais fácil);
- abrir o projeto em uma IDE e rodar a função main (VotoLyzeApplication.java);
- buildar você mesmo

## Executar JAR do projeto
O JAR do projeto com todas suas dependências está na pasta out\artifacts\VotoLyze_jar

Abrindo esta pasta no terminal, você pode executá-lo da seguinte maneira:

Windows:
```java -jar .\VotoLyze.jar```

Linux:
```java -jar ./VotoLyze.jar```



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
