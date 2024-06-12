# Por que o padrão repository ao invés do DAO utilizando Spring?
O padrão de repositório incentiva um design orientado a domínio, fornecendo uma compreensão mais fácil do domínio e da
estrutura de dados. Além disso, utilizando o repository do Spring não temos que nos preocupar em utilizar diretamente 
a API da JPA, bastando apenas criar os métodos que o Spring cria a implementação em tempo de execução, deixando o 
código muito mais simples, menor e legível.