# 📌 Serviço de Cadastro de Endereços

Este serviço faz parte de um sistema maior para **cadastro de pessoas e seus respectivos endereços**, desenvolvido em **Java com Spring Boot**. Ele é responsável por **armazenar múltiplos endereços relacionados a uma pessoa** no banco de dados.

## 🚀 Tecnologias utilizadas

- Java 17+
- Spring Boot
- Apache Camel (para integração entre serviços)
- PostgreSQL
- GitHub Actions (para CI/CD)

## 🔄 Funcionalidade

O serviço recebe um **payload JSON** de outro serviço via **Apache Camel**, contendo os dados do endereço e o ID da pessoa. Ele então processa e armazena essas informações no banco de dados.

### 📥 Exemplo de Payload recebido

```json
{
  "pessoaId": 42,
  "cep": "09060-730",
  "logradouro": "Rua Recife",
  "numero": "305",
  "complemento": "",
  "bairro": "Vila Sacadura Cabral",
  "localidade": "Santo André",
  "estado": "SP",
  "pais": "Brasil",
  "perfil": "Funcionario",
  "tipoDeEndereco": "RESIDENCIAL"
}
