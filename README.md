# Agenda Telefônica - Evolução do Projeto

Este repositório mostra a **evolução do projeto Agenda Telefônica** desde a versão inicial em Java puro até a versão Spring Boot, organizada em três branches principais.

---

## 1️⃣ Text Files Version (Original)

**Branch:** [`text-files-version-original`](https://github.com/seu-usuario/nome-do-repositorio/tree/text-files-version-original)  

Descrição:  
- Primeira versão do projeto em Java puro  
- Contatos armazenados em **arquivos de texto**  
- Funcionalidades: cadastrar, listar, editar e remover contatos  
- Interação via console (sem front-end)  

Esta versão demonstra a **base inicial e a lógica de programação** do sistema.

---

## 2️⃣ Database Version

**Branch:** [`database-version`](https://github.com/seu-usuario/nome-do-repositorio/tree/database-version)  

Descrição:  
- Evolução da versão original para uso de **MySQL** como banco de dados  
- Funcionalidades CRUD completas com persistência em banco  
- Mantém interação via console  
- Demonstra habilidade em **banco de dados e persistência de dados**  

---

## 3️⃣ Spring Boot Version

**Branch:** [`springboot-version`](https://github.com/seu-usuario/nome-do-repositorio/tree/springboot-version)  

Descrição:  
- Projeto migrado para **Spring Boot**, transformando o sistema de console em **API REST**  
- Estrutura organizada em **Controller → Service → Repository**  
- Funcionalidades CRUD de contatos via endpoints:  
  - `GET /contatos` → listar todos  
  - `POST /contatos` → criar novo contato  
  - `PUT /contatos/{id}` → atualizar contato  
  - `DELETE /contatos/{id}` → remover contato  
- Persistência em banco de dados MySQL usando **Spring Data JPA**  
- Pronto para integração futura com front-end  

Esta versão demonstra conhecimento em **Spring Boot, API REST, JPA e organização de projeto back-end**.

---

## ⚡ Observações

- Todas as versões são **sem interface gráfica**; o foco é back-end e lógica do sistema  
- Cada branch tem seu próprio README detalhando como rodar a versão específica  
- O branch `main` serve como **visão geral da evolução do projeto**  

---

> **Dica:** Substitua `seu-usuario` e `nome-do-repositorio` pelos reais do seu GitHub.  
