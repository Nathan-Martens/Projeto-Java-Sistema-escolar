# Sistema Acadêmico — POO com DAO + JDBC + MySQL

Projeto desenvolvido como parte das atividades avaliativas da disciplina de **Programação Orientada a Objetos**, implementado em Java com persistência em banco de dados MySQL via JDBC.

# Sobre o Projeto

Sistema de gerenciamento acadêmico que permite cadastrar e gerenciar **Alunos**, **Professores**, **Cursos** e **Departamentos**, com CRUD completo persistido em banco de dados relacional.

O projeto foi desenvolvido em **4 partes evolutivas**:

| Parte   | Descrição |
|---------|-----------|
| Parte 1 | Modelagem com herança, classes abstratas e polimorfismo |
| Parte 2 | Estruturas de dados em memória com `ArrayList` e menu interativo |
| Parte 3 | Organização em camadas com interface `Gerenciavel` |
| Parte 4 | Persistência com DAO + JDBC + MySQL  |

---

## Arquitetura

src/
├── Main.java                          # Execução e menu interativo
├── connection/
│   └── Conexao.java                   # Conexão JDBC com MySQL
├── interfaces/
│   └── Gerenciavel.java               # Interface com cadastrar() e listar()
├── models/
│   ├── Pessoa.java                    # Classe abstrata base
│   ├── Aluno.java                     # Herda de Pessoa
│   ├── Professor.java                 # Herda de Pessoa
│   ├── Curso.java                     # Entidade Curso
│   └── Departamento.java              # Entidade Departamento
├── dao/
│   ├── CursoDAOImpl.java              # CRUD de Cursos
│   ├── AlunoDAOImpl.java              # CRUD de Alunos
│   ├── ProfessorDAOImpl.java          # CRUD de Professores
│   └── DepartamentoDAOImpl.java       # CRUD de Departamentos + associações
└── sistema/
    └── SistemaAcademico.java          # Implementa Gerenciavel, delega aos DAOs

## Conceitos de POO Aplicados

- **Herança** — `Aluno` e `Professor` herdam de `Pessoa`
- **Abstração** — classe `Pessoa` abstrata com método `exibirDados()` abstrato
- **Polimorfismo** — cada subclasse implementa `exibirDados()` à sua maneira
- **Encapsulamento** — todos os atributos privados com getters/setters
- **Interface** — `Gerenciavel<T>` define o contrato de cadastro e listagem
- **DAO Pattern** — separação clara entre lógica de negócio e acesso a dados

## Banco de Dados

### Tabelas

sql
cursos              → id, nome
alunos              → id, nome, cpf, ra, curso_id (FK), ...
professores         → id, nome, cpf, especializacao, ...
departamentos       → id, nome
departamento_aluno  → departamento_id (FK), aluno_id (FK)
departamento_professor → departamento_id (FK), professor_id (FK)

### Script SQL

O arquivo `banco.sql` cria o banco `sistema_academico` e todas as tabelas automaticamente.

##  Como Executar

### Pré-requisitos

- Java 11+
- IntelliJ IDEA
- XAMPP (MySQL + Apache)
- [`mysql-connector-j-9.3.0.jar`](https://dev.mysql.com/downloads/connector/j/)

### Passo a passo

**1. Banco de dados**

Abra o XAMPP → inicie o MySQL → acesse o phpMyAdmin
Importe ou cole o conteúdo do arquivo banco.sql


**2. Configurar o conector no IntelliJ**

File → Project Structure → Libraries → + → Java
Selecione: lib/mysql-connector-j-9_3_0.jar
Clique OK → Apply → OK


**3. Rodar o projeto**

Execute a classe Main.java

## Funcionalidades

- [x] Cadastrar Aluno (vinculado a um Curso)
- [x] Cadastrar Professor
- [x] Cadastrar Curso
- [x] Listar Alunos por Curso
- [x] Listar Professores
- [x] Listar Cursos com seus Alunos
- [x] Editar Aluno (nome e telefone)
- [x] Editar Professor (nome e especialização)
- [x] Criar Departamento
- [x] Associar Aluno a Departamento
- [x] Associar Professor a Departamento
- [x] Listar Departamentos com membros


##  Tecnologias

![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![MySQL](https://img.shields.io/badge/MySQL-005C84?style=for-the-badge&logo=mysql&logoColor=white)
![IntelliJ IDEA](https://img.shields.io/badge/IntelliJ_IDEA-000000?style=for-the-badge&logo=intellij-idea&logoColor=white)

- **Linguagem:** Java
- **Banco de Dados:** MySQL
- **Conexão:** JDBC com `PreparedStatement`
- **IDE:** IntelliJ IDEA
- **Ambiente local:** XAMPP



## Estrutura de Pastas


SistemaAcademico/
├── banco.sql
├── lib/
│   └── mysql-connector-j-9_3_0.jar
└── src/
    ├── Main.java
    ├── connection/
    ├── interfaces/
    ├── models/
    ├── dao/
    └── sistema/

## Autor

Desenvolvido para a disciplina de **Programação Orientada a Objetos**.
