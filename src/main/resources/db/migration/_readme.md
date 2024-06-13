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