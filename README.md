# Projeto para treino de API com Spring
## Configuração
```
Java 17
JDK - JetBrains Runtime version 17.0.8
Maven Project
Packaging: Jar
```
## Dependenciais
```
Spring Boot DevTools
Lombok
Spring Web
```
## O que é esse projeto?




## Como é montado o projeto?
### Conexão com MySQL
```
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-jpa</artifactId>
</dependency>
<dependency>
    <groupId>com.mysql</groupId>
    <artifactId>mysql-connector-j</artifactId>
    <scope>runtime</scope>
</dependency>
```
Utilizando essas dependencias e adicionando as chaves de conexão em "src/main/resources/application.properties" 
iremos preencher as url do banco utilizado e o acesso ao usuário e senha.
```
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.datasource.url=jdbc:mysql://localhost/vollmed_api
spring.datasource.username=escreverusuarioMySQL
spring.datasource.password=escreversenhaMySQL
```
