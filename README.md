# Breakfast-API

#### O Breakfast é um projeto que tem o propósito de auxiliar na organização de um café da manhã em grupo com os colaboradores da empresa em que você trabalha.

-  **Neste repositório está contido apenas o Back End deste projeto, uma REST API com as operações básicas de um CRUD.**


- **Você pode encontrar o repositório do Front End acessando <a href="https://github.com/marcoswinicios/breakfast-web">Breakfast-web</a>**


- **Esta API Está hospedada no Heroku. Portanto, você pode acessar seus end points a partir do seguinte endereço: <a href="https://breakfast-api.herokuapp.com">https://breakfast-api.herokuapp.com</a>**
---
## Funcionalidades
#### Dados cadastrados
- Cadastro de  Colaboradores
    - Nome do colaborador
    - CPF do colaborador
- Cadastro de itens para o café da manhã
    - Nome do item. Ex.: Pão, Bolo, etc.
#### Regras de negócio
- Utilizar NativeQuery para inserção, atualização, consulta e exclusão.
- Não pode repetir cpf.
- Não pode repetir opção de café da manhã mesmo que seja outro colabor

## Tecnologias utilizadas 
- Java 11
- PostgresSQL
- Spring Boot
- Spring Data JPA
- Spring Boot Dev Tools
- Flyway
- Beans Validation
- Model Mapper
- Spring Security
- Apache Maven
---
## Testando a API localmente
#### Ferramentas que você deve instalar em seu computador

**Obs.:** Deixei apenas o link do site oficial de cada ferramenta para que você possa seguir o passo a passo de instalação em seu sistema operacional

- <a href="https://git-scm.com/">Git</a>
- <a href="https://www.oracle.com/br/java/technologies/javase/jdk11-archive-downloads.html">Java JDK 11</a>
- <a href="https://maven.apache.org/"> Apache Maven</a>
- IDE (Escolha uma das seguintes opções)
    - <a href="https://spring.io/tools">Spring Tools Switch</a>
    - <a href="https://www.eclipse.org/downloads/">Eclipse</a>
    
- <a href="https://www.postgresql.org/">PostgresSQL</a>
- <a href="https://www.pgadmin.org/">PgAdmin</a>
- <a href="https://www.postman.com/">Postman</a>
---
#### Passo a passo para executar o projeto

##### 1. Clonando repositório
1.1 Com o Git instalado, Abra o terminal em uma diretório de sua escolha e execute o seguinte comando:
```
git clone https://github.com/MarcosWinicios/breakfast-api.git
```
##### 2. Executando o projeto 
2.1 Abrir a IDE escolhida
2.2 Importar o projeto selecionando o diretório onde clonou este repositório
2.3 Esperar o Maven instalar as dependências descritas no pom.xml
2.4 Banco de dados
    - Abra o PgAdmin
    - Crie um servidor local
    - Crie um banco de dados com o nome `breakfast`
2.5 Configurando acesso ao banco de dados no projeto
    - Edite o arquivo `application.properties` dentro de `src/main/resources` inserindo os suas configurações de conexão com o banco.
<br/>
    Exemplo:

            spring.datasource.url=jdbc:postgresql://localhost:5432/breakfast?serverTimezone=UTC
            spring.datasource.username=postgres
            spring.datasource.password=123456
            spring.jpa.database-platform=org.hibernate.dialect.PostgreSQL9Dialect
            spring.jpa.hibernate.ddl-auto=update
2.5 Inicialize o projeto
- Com o banco de dados criado o Flyway criará todas as tabelas automaticamente utilizando as migrations
---
## Acessando endpoints

- Com o projeto em execução abra o `Postman`
- Acesse os end points do projeto pelo endereço: `http:localhost:8080`

### Guia de referência da API

#### End points referente aos dados do Colaborador 
- Listar todos os colaboradores
```http
  GET /employees
```
- Bucar colaborador por id
```http
  GET /employees/{id}
```

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `id` | `number` | Id do colaborador a ser buscado |

- Bucar colaboradores por nome
```http
  GET /employees/name/{name}
```

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `name` | `string` | Nome do colaborador a ser buscado |

- Bucar colaborador por cpf
```http
  GET /employees/cpf/{cpf}
```

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `cpf` | `string` | CPF do colaborador a ser buscado |

- Listar apenas os itens de um colaborador
```http
  GET /employees/{id}/items
```

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `id` | `number` | id do colaborador |

- Cadastrar colaborador
```http
  POST /employees
```

```
{   
    "name": "Fulano" ,
    "cpf": "123456789012"
}   
```
| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `id` | `number` | id do colaborador a ser atualizado|

- Atualizar colaborador
```http
  PUT /employees/{id}
```
```
{   
    "name": "Fulano" ,
    "cpf": "123456789012"
}   
```

- Deletar colaborador
```http
  DELETE /employees/{id}
```

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `id` | `number` | id do colaborador a ser deletado|

---

#### End points referente aos dados dos Itens 

- Listar todos os itens
```http
  GET /items/
```


- Buscar item por id
```http
  GET /items/{id}
```

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `id` | `number` | id do item|

- Buscar item por nome
```http
  GET /items/{name}
```

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `name` | `string` | Nome do item|

- Cadastrar item
```http
  POST /items
```

```
{
  "employee":{
    "id": 3
  },
  "itemName": "Bolo De Chocolate"
}   
```

- Atualizar item
```http
  PUT /items/{id}
```

```
{
  "employee":{
    "id": 3
  },
  "itemName": "Bolo De Chocolate"
}   
```
| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `id` | `string` | id do item a ser atualizado|


- Deletar item
```http
  DELETE /items/{id}
```

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `id` | `number` | id do item a ser deletado|


- Você pode utilizar os seguintes scripts para inserir dados no banco de de dados


```
    INSERT INTO tb_employee (name, cpf) VALUES('Marcos Winicios', '11111111111');
    INSERT INTO tb_employee (name, cpf) VALUES('Joao Alves', '22222222222');
    INSERT INTO tb_employee (name, cpf) VALUES('Bill Gates', '33333333333');
    INSERT INTO tb_employee (name, cpf) VALUES('Steve Jobs', '44444444444');
    INSERT INTO tb_employee (name, cpf) VALUES('Linus Turvalds', '55555555555');
    INSERT INTO tb_employee (name, cpf) VALUES('Linus Turvalds', '66666666666');

    INSERT INTO tb_item (name, employee_id) VALUES ('Pão de queijo', 1);
    INSERT INTO tb_item (name, employee_id) VALUES ('Café', 1);
    INSERT INTO tb_item (name, employee_id) VALUES ('Bolo de cenoura', 2);
    INSERT INTO tb_item (name, employee_id) VALUES ('Leite', 4);
    INSERT INTO tb_item (name, employee_id) VALUES ('Pão Francês', 2);
    INSERT INTO tb_item (name, employee_id) VALUES ('Achocolatado', 2);

```