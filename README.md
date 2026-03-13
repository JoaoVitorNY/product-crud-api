# 🚀 Product CRUD API

API RESTful desenvolvida com **Spring Boot** para gerenciamento de produtos, permitindo operações completas de **CRUD (Create, Read, Update, Delete)**.

Projeto focado em boas práticas de desenvolvimento backend, organização em camadas e integração com banco de dados relacional.

---

## 📌 Sobre o Projeto

Esta API foi criada com o objetivo de praticar:

- Arquitetura em camadas (Controller, Service, Repository)
- Desenvolvimento de APIs REST
- Integração com banco de dados usando JPA/Hibernate
- Boas práticas de organização e estrutura de projeto
- Versionamento com Git

---

## 🛠️ Tecnologias Utilizadas

- ☕ Java  
- 🌱 Spring Boot  
- 🗄️ Spring Data JPA  
- 🐘 PostgreSQL 
- 🔧 Maven  
- 📬 Postman (para testes)

---

## 🏗️ Arquitetura do Projeto

O projeto segue o padrão de arquitetura em camadas:

```
src/main/java
 ├── controller   → Camada responsável pelas requisições HTTP
 ├── service      → Regras de negócio
 ├── repository   → Comunicação com o banco de dados
 ├── model        → Entidades da aplicação
```

## 🔗 Endpoints da API

| Método | Endpoint | Descrição |
|------|------|------|
| POST | `/produtos` | Cadastrar novo produto |
| GET | `/produtos` | Listar todos os produtos |
| GET | `/produtos/{id}` | Buscar produto por ID |
| PUT | `/produtos/{id}` | Atualizar produto |
| DELETE | `/produtos/{id}` | Remover produto |

---

## 📦 Entidade Produto

Exemplo de estrutura da entidade:

```json
{
  "id": 1,
  "nome": "Notebook",
  "descricao": "Notebook Gamer RTX",
  "preco": 4500.00,
  "quantidade": 10
}
```
