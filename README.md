# 🏴‍☠️ Registro de Piratas - One Piece API

Este projeto foi desenvolvido com base no universo de **One Piece**, com o objetivo de registrar e gerenciar informações sobre **piratas**, suas **tripulações** e suas respectivas **Akuma no Mi**.

A aplicação foi construída utilizando **Java com Spring Boot**, adotando boas práticas como **arquitetura em camadas**, controle de versão de banco com **Flyway**, e uso de **Docker Compose** para orquestração do ambiente com **PostgreSQL**.

---

## 🚀 Tecnologias Utilizadas

- Java 17+
- Spring Boot
  - Spring Web
  - Spring Data JPA
- PostgreSQL
- Flyway
- Docker & Docker Compose
- Swagger / OpenAPI

---

## 🏴‍☠️ Rotas para Piratas  
| Método | Endpoint                  | Descrição                          |
|--------|---------------------------|------------------------------------|
| GET    | /piratas                  | Lista todos os piratas             |
| GET    | /piratas/{id}             | Busca pirata por ID                |
| POST   | /piratas                  | Cria novo pirata                   |
| PUT    | /piratas/{id}             | Atualiza todos os dados do pirata  |
| PATCH  | /piratas/{id}             | Edita parcialmente um pirata       |
| DELETE | /piratas/{id}             | Exclui pirata por ID               |

## 🍈 Rotas para Akuma no Mi  
| Método | Endpoint                  | Descrição                               |
|--------|---------------------------|-----------------------------------------|
| GET    | /akumanomis               | Lista todas as akuma no mi              |
| GET    | /akumanomis/{id}          | Busca akuma no mi por ID                |
| POST   | /akumanomis               | Cria nova akuma no mi                   |
| PUT    | /akumanomis/{id}          | Atualiza todos os dados da akuma no mi  |
| PATCH  | /akumanomis/{id}          | Edita parcialmente uma akuma no mi      |
| DELETE | /akumanomis/{id}          | Exclui akuma no mi por ID               |

## ⛵ Rotas para Tripulações  
| Método | Endpoint                     | Descrição                               |
|--------|------------------------------|-----------------------------------------|
| GET    | /tripulacoes                 | Lista todas as tripulações              |
| GET    | /tripulacoes/{id}            | Busca tripulação por ID                 |
| POST   | /tripulacoes                 | Cria nova tripulação                    |
| PUT    | /tripulacoes/{id}            | Atualiza dados da tripulação            |
| PATCH  | /tripulacoes/{id}            | Edita parcialmente uma tripulação       |
| DELETE | /tripulacoes/{id}            | Exclui tripulação por ID                |

---

## 🔧 Variáveis de Ambiente

Crie um arquivo `.env` na raiz do projeto com o seguinte conteúdo:

```env
# Variáveis utilizadas para configurar o banco PostgreSQL no Docker
CONTAINER_NAME={CONTAINER_NAME}
DB_NAME={DB_NAME}
DB_USER={DB_USER}
DB_PASSWORD={DB_PASSWORD}
DB_PORTS={DB_PORTS}
DB_VOLUMES={DB_VOLUMES}

# URL de conexão com o banco (construída conforme padrão PostgreSQL)
DB_URL=jdbc:postgresql://localhost:{DB_PORTS}/{DB_NAME}
```
---

## 📦 Como rodar o projeto

### Pré-requisitos

- Docker e Docker Compose instalados
- JDK 17 ou superior (caso rode localmente sem docker)

### Passo a passo com Docker

```bash
# 1. Clone o repositório
git clone https://github.com/seu-usuario/nome-do-repositorio.git
cd nome-do-repositorio

# 2. Suba o ambiente com PostgreSQL
docker-compose up -d

# 3. Rode a aplicação (caso use Maven)
./mvnw spring-boot:run
```
A aplicação estará disponível em:
📍 http://localhost:8080

---

🧭 Documentação da API
A documentação interativa da API pode ser acessada via Swagger UI:

🔗 http://localhost:8080/swagger-ui.html
