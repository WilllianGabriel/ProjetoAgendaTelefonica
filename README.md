# Agenda Telefônica - Spring Boot Version

Esta versão do projeto foi migrada para **Spring Boot**, transformando a aplicação de console em **API REST**, mantendo o foco no back-end.

## Funcionalidades
- CRUD de contatos (nome e telefone) via **API REST**
  - `GET /contatos` → lista todos os contatos
  - `POST /contatos` → cria novo contato
  - `PUT /contatos/{id}` → atualiza contato existente
  - `DELETE /contatos/{id}` → remove contato
- Persistência em banco de dados MySQL usando **Spring Data JPA**
- Estrutura organizada em:
  - **Controller** → endpoints
  - **Service** → lógica do negócio
  - **Repository** → interface com banco de dados
- Preparado para adicionar **front-end** futuramente

## Requisitos
- Java 17
- Maven
- Spring Boot 4.x
- MySQL (XAMPP ou outro)
- IDE (IntelliJ ou Eclipse)

## Como executar
1. Configure o banco de dados no MySQL (`application.properties`):
   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/seubanco
   spring.datasource.username=root
   spring.datasource.password=senha
