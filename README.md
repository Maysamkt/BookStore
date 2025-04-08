# 📚 Sistema de Gerenciamento de Livros

Este projeto é um sistema simples para gerenciamento de autores, editoras e livros, utilizando **Java com Hibernate** como ORM e **MySQL** como banco de dados relacional.

## 🚀 Tecnologias Utilizadas

- Java 21
- Hibernate ORM
- MySQL
- Maven 
- JDBC

## 🧱 Estrutura do Sistema

O sistema implementa relacionamentos entre as entidades:

- **Livro** possui um autor e uma editora.
- **Autor** pode ter várias editoras (relacionamento muitos-para-muitos).
- **Editora** pode estar associada a vários autores.

### Consultas implementadas:

- 📗 Livros por autor
- 👨‍💼 Autores por livro
- 🏛️ Editoras por livro
- 📘 Livros por editora
- 👨‍💼 Autores por editora
- 🏢 Editoras por autor

## 🗃️ Entidades

- `Autor`: `id`, `nome`
- `Editora`: `id`, `nome`
- `Livro`: `id`, `titulo`, `isbn`, `ano_pub`, `autor`, `editora`
- `autor_editora`: tabela de junção para relacionamento N:N

## ⚙️ Como Executar

1. Crie um banco de dados MySQL com o nome `bookstore`.
2. Configure o arquivo de conexão do Hibernate (`hibernate.cfg.xml`), com as credenciais e URL corretas.
3. Execute a classe `Principal.java` para iniciar o sistema e executar as operações.
4. As tabelas serão criadas automaticamente e dados de exemplo serão inseridos.

## 📝 Observações

- O Hibernate gerencia automaticamente a criação e exclusão das tabelas ao iniciar o projeto.
- Caso o schema já exista, erros de `drop foreign key` podem ocorrer se as tabelas já foram excluídas anteriormente. Esses erros são tratados e não impedem o funcionamento da aplicação.
- Certifique-se de que o MySQL está em execução e acessível.

---

Feito com 💻 por Maysa Santos
