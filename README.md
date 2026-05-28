# Financeiro API

API REST criada com Java 21, Spring Boot, Spring Data JPA, MySQL e Springdoc Swagger.

## Estrutura implementada

Foram criados CRUDs completos para:

- Empresa
- Usuário
- Categoria
- Conta
- Transação

## Tecnologias

- Java 21
- Spring Boot 3.5.14
- Spring Web
- Spring Data JPA
- Bean Validation
- MySQL Connector/J 8.0.33
- Springdoc OpenAPI Swagger UI 2.8.17

## Observação sobre o MySQL

O dump enviado informa servidor MySQL 5.7.30. Por isso, o projeto foi configurado com `mysql-connector-j` 8.0.33, e não com o driver legado `mysql-connector-java` 5.1.

Motivo: Java 21 + Spring Boot 3 usam Jakarta EE e bibliotecas atuais. O driver 5.1 é antigo e pode gerar incompatibilidades. O Connector/J 8.0.x é uma escolha mais segura para MySQL 5.7.

## Configuração do banco

Edite o arquivo:

```text
src/main/resources/application.properties
```

Ajuste:

```properties
spring.datasource.username=SEU_USUARIO
spring.datasource.password=SUA_SENHA
```

A URL já está apontando para o banco informado no dump:

```properties
spring.datasource.url=jdbc:mysql://edumysql.acesso.rj.senac.br:3306/20261_prjint3_manha_danyelgranja?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true&characterEncoding=latin1
```

## Rodando localmente com Docker

Caso queira testar sem usar o banco remoto, execute:

```bash
docker compose up -d
```

Depois rode a aplicação usando o profile local:

```bash
mvn spring-boot:run -Dspring-boot.run.profiles=local
```

## Rodando com o banco remoto

```bash
mvn spring-boot:run
```

## Swagger

Depois que a aplicação subir, acesse:

```text
http://localhost:8080/swagger-ui.html
```

Documentação OpenAPI em JSON:

```text
http://localhost:8080/v3/api-docs
```

## Endpoints

### Empresas

```text
GET    /api/empresas
GET    /api/empresas/{id}
POST   /api/empresas
PUT    /api/empresas/{id}
DELETE /api/empresas/{id}
```

### Usuários

```text
GET    /api/usuarios
GET    /api/usuarios/{id}
POST   /api/usuarios
PUT    /api/usuarios/{id}
DELETE /api/usuarios/{id}
```

### Categorias

```text
GET    /api/categorias
GET    /api/categorias/{id}
POST   /api/categorias
PUT    /api/categorias/{id}
DELETE /api/categorias/{id}
```

### Contas

```text
GET    /api/contas
GET    /api/contas/{id}
POST   /api/contas
PUT    /api/contas/{id}
DELETE /api/contas/{id}
```

### Transações

```text
GET    /api/transacoes
GET    /api/transacoes/{id}
POST   /api/transacoes
PUT    /api/transacoes/{id}
DELETE /api/transacoes/{id}
```

## Exemplos de JSON

### Criar empresa

```json
{
  "nome": "Empresa Exemplo",
  "cnpj": "00.000.000/0001-00",
  "status": true
}
```

### Criar usuário

```json
{
  "nome": "Usuário Exemplo",
  "email": "usuario@exemplo.com",
  "senha": "123456",
  "status": true,
  "foto": null,
  "empresaId": 1
}
```

### Criar categoria

```json
{
  "nome": "Salário",
  "tipo": "entrada",
  "usuarioId": 1
}
```

Valores aceitos para `tipo`:

```text
entrada
saida
```

### Criar conta

```json
{
  "descricao": "Aluguel de maio",
  "valor": 1500.00,
  "valorPago": null,
  "tipo": "pagar",
  "status": "pendente",
  "dataVencimento": "2026-05-10",
  "dataPagamento": null,
  "categoriaId": 2,
  "usuarioId": 1
}
```

Valores aceitos para `tipo`:

```text
pagar
receber
```

Valores aceitos para `status`:

```text
pendente
efetivado
```

### Criar transação

```json
{
  "descricao": "Recebimento de salário",
  "valor": 5000.00,
  "data": "2026-05-05",
  "categoriaId": 1,
  "usuarioId": 1,
  "contaId": null
}
```

## Script SQL

O script com a estrutura das tabelas está em:

```text
src/main/resources/db/schema.sql
```

Há também um arquivo com dados de exemplo:

```text
src/main/resources/db/data-example.sql
```

## Observação didática

A senha do usuário foi mantida como campo simples porque o banco informado possui apenas `usuario_senha`. Em projeto real, a senha deve ser criptografada com BCrypt e nunca retornada pela API. Neste projeto, o campo senha não é retornado nos DTOs de resposta.
