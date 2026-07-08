CREATE DATABASE IF NOT EXISTS sistema_academico
    CHARACTER SET utf8mb4
    COLLATE utf8mb4_unicode_ci;

USE sistema_academico;

--  Cursos
CREATE TABLE IF NOT EXISTS cursos (
    id   INT          NOT NULL AUTO_INCREMENT,
    nome VARCHAR(100) NOT NULL,
    PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--  Departamentos
CREATE TABLE IF NOT EXISTS departamentos (
    id   INT          NOT NULL AUTO_INCREMENT,
    nome VARCHAR(100) NOT NULL,
    PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--  Alunos
CREATE TABLE IF NOT EXISTS alunos (
    id           INT          NOT NULL AUTO_INCREMENT,
    nome         VARCHAR(100) NOT NULL,
    telefone     VARCHAR(20),
    endereco     VARCHAR(200),
    cpf          VARCHAR(14)  NOT NULL,
    estado_civil VARCHAR(30),
    email        VARCHAR(100),
    data_nasc    VARCHAR(20),
    ra           VARCHAR(20)  NOT NULL,
    historico    VARCHAR(500),
    ativo        INT          NOT NULL DEFAULT 1,
    curso_id     INT,
    PRIMARY KEY (id),
    FOREIGN KEY (curso_id) REFERENCES cursos(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Professores
CREATE TABLE IF NOT EXISTS professores (
    id              INT          NOT NULL AUTO_INCREMENT,
    nome            VARCHAR(100) NOT NULL,
    telefone        VARCHAR(20),
    endereco        VARCHAR(200),
    cpf             VARCHAR(14)  NOT NULL,
    estado_civil    VARCHAR(30),
    email           VARCHAR(100),
    data_nasc       VARCHAR(20),
    especializacao  VARCHAR(100),
    ativo           INT          NOT NULL DEFAULT 1,
    PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Associações Departamento
CREATE TABLE IF NOT EXISTS departamento_aluno (
    departamento_id INT NOT NULL,
    aluno_id        INT NOT NULL,
    PRIMARY KEY (departamento_id, aluno_id),
    FOREIGN KEY (departamento_id) REFERENCES departamentos(id),
    FOREIGN KEY (aluno_id)        REFERENCES alunos(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE IF NOT EXISTS departamento_professor (
    departamento_id INT NOT NULL,
    professor_id    INT NOT NULL,
    PRIMARY KEY (departamento_id, professor_id),
    FOREIGN KEY (departamento_id) REFERENCES departamentos(id),
    FOREIGN KEY (professor_id)    REFERENCES professores(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
