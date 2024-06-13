# Ferramentas de Migrações (Migrations)

```
<dependency>
    <groupId>org.flywaydb</groupId>
    <artifactId>flyway-core</artifactId>
</dependency>
<dependency>
    <groupId>org.flywaydb</groupId>
    <artifactId>flyway-mysql</artifactId>
</dependency>
```
 Com essas dependenciais é possivel fazer migração utilizando caminho "resource/db/migrations" ao executar o 
 projeto. Para que os arquivos sejam lido é necessário escrever V (maiusculo), numero da ordem, dois anderline e 
 em seguida o nome do arquivo com o formato .sql
 
Exemplo:
- V1__create-table-medicos.sql
- V2__create-table-paciente.sql

Com isso a conexão com banco de dados e a transmissão de informações está funcional


## Atenção 1
Se uma migration for executada, não é possivel edita-la! Isso pode causar problemas! Ela precisa ser imutável, 
caso necessite realizar alguma mudança, é necessário criar um novo migration.

## Atenção 2
Caso você escreva uma nova versão do migration com o projeto rodando, pode acontecer problema e travar seu sistema.
Para resolver é necessário acessar o banco de dados da aplicação e executar o comando:
```mysql
delete from flyway_schema_history where success = 0;
```
Caso a migration tenha criado uma tabela ou clunas o problema vai persistir, nesse caso é necessário apagar banco de 
dados e criá-lo novamente:
```mysql
drop database vollmed_api;
create database vollmed_api;
```