# Agenda Telefônica - Database Version

Esta versão do projeto foi evoluída para usar **MySQL** (via XAMPP) em vez de arquivos de texto para armazenamento.

## Funcionalidades
- Cadastrar contatos com nome e número de telefone
- Listar contatos cadastrados
- Editar contatos
- Remover contatos
- Persistência em banco de dados MySQL

## Requisitos
- Java 17 ou superior
- MySQL (XAMPP ou outro)
- IDE (Eclipse ou IntelliJ)

## Como executar
1. Configure o banco de dados no MySQL:
   - Nome do banco: `agendatelefonica`
   - Crie a tabela `contatos` (ou execute script SQL, se houver)
2. Atualize as credenciais no código (`username`, `password`)
3. Abra o projeto na IDE
4. Execute a classe `Main.java`
5. Interaja pelo console

## Observações
- Projeto ainda **não possui interface gráfica**
- Mostra evolução do projeto do armazenamento em arquivo de texto para banco de dados
