import models.*;
import sistema.SistemaAcademico;

import java.util.List;
import java.util.Scanner;

/**
 * Main – responsável apenas pela execução e testes do sistema.
 * Toda lógica de negócio e persistência está em SistemaAcademico e nos DAOs.
 */
public class Main {

    static SistemaAcademico sistema = new SistemaAcademico();
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        int opcao;

        do {
            System.out.println("\n===== SISTEMA ACADÊMICO =====");
            System.out.println("1 - Inserir Aluno");
            System.out.println("2 - Inserir Professor");
            System.out.println("3 - Inserir Curso");
            System.out.println("4 - Listar Alunos");
            System.out.println("5 - Listar Professores");
            System.out.println("6 - Listar Cursos com Alunos");
            System.out.println("7 - Editar Aluno");
            System.out.println("8 - Editar Professor");
            System.out.println("9 - Criar Departamento");
            System.out.println("10 - Associar Aluno ao Departamento");
            System.out.println("11 - Associar Professor ao Departamento");
            System.out.println("12 - Listar Departamentos");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opção: ");

            opcao = sc.nextInt();
            sc.nextLine();

            switch (opcao) {
                case 1:
                    inserirAluno();
                    break;
                case 2:
                    inserirProfessor();
                    break;
                case 3:
                    inserirCurso();
                    break;
                case 4:
                    listarAlunos();
                    break;
                case 5:
                    listarProfessores();
                    break;
                case 6:
                    listarCursos();
                    break;
                case 7:
                    editarAluno();
                    break;
                case 8:
                    editarProfessor();
                    break;
                case 9:
                    criarDepartamento();
                    break;
                case 10:
                    associarAlunoDepartamento();
                    break;
                case 11:
                    associarProfessorDepartamento();
                    break;
                case 12:
                    listarDepartamentos();
                    break;
                case 0:
                    System.out.println("Sistema encerrado.");
                    break;
                default:
                    System.out.println("Opção inválida!");
            }

        } while (opcao != 0);
    }

    // ── INSERIR ALUNO ────────────────────────────────────────────────────
    public static void inserirAluno() {
        List<Curso> cursos = sistema.listarCursos();
        if (cursos.isEmpty()) {
            System.out.println("Nenhum curso cadastrado! Cadastre um curso antes de adicionar alunos.");
            return;
        }

        System.out.println("\n--- Cadastro de Aluno ---");
        System.out.print("Nome: ");
        String nome = sc.nextLine();
        System.out.print("Telefone: ");
        String telefone = sc.nextLine();
        System.out.print("Endereço: ");
        String endereco = sc.nextLine();
        System.out.print("CPF: ");
        String cpf = sc.nextLine();
        System.out.print("Estado Civil: ");
        String estadoCivil = sc.nextLine();
        System.out.print("Email: ");
        String email = sc.nextLine();
        System.out.print("Data Nascimento: ");
        String dataNasc = sc.nextLine();
        System.out.print("RA: ");
        String ra = sc.nextLine();
        System.out.print("Histórico: ");
        String historico = sc.nextLine();

        System.out.println("Escolha o curso:");
        for (int i = 0; i < cursos.size(); i++) {
            System.out.println(i + " - " + cursos.get(i).getNome());
        }
        int opcaoCurso = sc.nextInt();
        sc.nextLine();

        if (opcaoCurso < 0 || opcaoCurso >= cursos.size()) {
            System.out.println("Curso inválido!");
            return;
        }

        Curso cursoSelecionado = cursos.get(opcaoCurso);
        Aluno aluno = new Aluno(nome, telefone, endereco, cpf, estadoCivil, email, dataNasc, ra, historico, 1, cursoSelecionado);

        if (sistema.cadastrar(aluno)) {
            System.out.println("Aluno cadastrado com sucesso! ID: " + aluno.getId());
        } else {
            System.out.println("Erro ao cadastrar aluno.");
        }
    }

    // ── INSERIR PROFESSOR ────────────────────────────────────────────────
    public static void inserirProfessor() {
        System.out.println("\n--- Cadastro de Professor ---");
        System.out.print("Nome: ");
        String nome = sc.nextLine();
        System.out.print("Telefone: ");
        String telefone = sc.nextLine();
        System.out.print("Endereço: ");
        String endereco = sc.nextLine();
        System.out.print("CPF: ");
        String cpf = sc.nextLine();
        System.out.print("Estado Civil: ");
        String estadoCivil = sc.nextLine();
        System.out.print("Email: ");
        String email = sc.nextLine();
        System.out.print("Data Nascimento: ");
        String dataNasc = sc.nextLine();
        System.out.print("Especialização: ");
        String especializacao = sc.nextLine();

        Professor professor = new Professor(nome, telefone, endereco, cpf, estadoCivil, email, dataNasc, especializacao, 1);

        if (sistema.cadastrarProfessor(professor)) {
            System.out.println("Professor cadastrado com sucesso! ID: " + professor.getId());
        } else {
            System.out.println("Erro ao cadastrar professor.");
        }
    }

    // ── INSERIR CURSO ────────────────────────────────────────────────────
    public static void inserirCurso() {
        System.out.print("Nome do curso: ");
        String nome = sc.nextLine();
        Curso curso = new Curso(nome);
        if (sistema.cadastrarCurso(curso)) {
            System.out.println("Curso cadastrado com sucesso! ID: " + curso.getId());
        } else {
            System.out.println("Erro ao cadastrar curso.");
        }
    }

    // ── LISTAR ALUNOS ────────────────────────────────────────────────────
    public static void listarAlunos() {
        List<Curso> cursos = sistema.listarCursos();
        if (cursos.isEmpty()) {
            System.out.println("Nenhum curso cadastrado.");
            return;
        }

        for (Curso c : cursos) {
            System.out.println("\nCurso: " + c.getNome());
            List<Aluno> alunos = sistema.listarAlunosPorCurso(c.getId());
            if (alunos.isEmpty()) {
                System.out.println("Nenhum aluno neste curso.");
            } else {
                for (Aluno a : alunos) {
                    System.out.println(a.exibirDados());
                    System.out.println("-------------------");
                }
            }
        }
    }

    // ── LISTAR PROFESSORES ───────────────────────────────────────────────
    public static void listarProfessores() {
        List<Professor> lista = sistema.listarProfessores();
        if (lista.isEmpty()) {
            System.out.println("Nenhum professor cadastrado.");
            return;
        }

        for (int i = 0; i < lista.size(); i++) {
            Professor p = lista.get(i);
            System.out.println("\nProfessor N°: " + (i + 1));
            System.out.println("Nome: " + p.getNome());
            System.out.println("Especialização: " + p.getEspecializacao());
        }
    }

    // ── LISTAR CURSOS COM ALUNOS ─────────────────────────────────────────
    public static void listarCursos() {
        List<Curso> cursos = sistema.listarCursos();
        if (cursos.isEmpty()) {
            System.out.println("Nenhum curso cadastrado.");
            return;
        }

        System.out.println("\n===== LISTA DE CURSOS E ALUNOS =====");
        for (int i = 0; i < cursos.size(); i++) {
            Curso c = cursos.get(i);
            System.out.println("\nCurso ID: " + c.getId());
            System.out.println("Nome: " + c.getNome());

            List<Aluno> alunos = sistema.listarAlunosPorCurso(c.getId());
            if (alunos.isEmpty()) {
                System.out.println("Nenhum aluno matriculado.");
            } else {
                System.out.println("Alunos:");
                for (int j = 0; j < alunos.size(); j++) {
                    Aluno a = alunos.get(j);
                    System.out.println("  [" + j + "] " + a.getNome() + " | RA: " + a.getRaDoAluno());
                }
            }
            System.out.println("-----------------------------------");
        }
    }

    // ── EDITAR ALUNO ─────────────────────────────────────────────────────
    public static void editarAluno() {
        List<Curso> cursos = sistema.listarCursos();
        if (cursos.isEmpty()) {
            System.out.println("Nenhum curso cadastrado.");
            return;
        }

        System.out.println("\nEscolha o curso:");
        for (int i = 0; i < cursos.size(); i++) {
            System.out.println(i + " - " + cursos.get(i).getNome());
        }
        int opcaoCurso = sc.nextInt();
        sc.nextLine();
        Curso cursoSelecionado = cursos.get(opcaoCurso);

        List<Aluno> alunos = sistema.listarAlunosPorCurso(cursoSelecionado.getId());
        if (alunos.isEmpty()) {
            System.out.println("Nenhum aluno neste curso.");
            return;
        }

        for (int i = 0; i < alunos.size(); i++) {
            System.out.println("[" + i + "] " + alunos.get(i).getNome() + " | RA: " + alunos.get(i).getRaDoAluno());
        }

        System.out.print("Digite o ID do aluno: ");
        int idx = sc.nextInt();
        sc.nextLine();

        if (idx >= 0 && idx < alunos.size()) {
            Aluno a = alunos.get(idx);
            System.out.print("Novo nome: ");
            a.setNome(sc.nextLine());
            System.out.print("Novo telefone: ");
            a.setTelefone(sc.nextLine());
            if (sistema.atualizarAluno(a)) {
                System.out.println("Aluno atualizado!");
            } else {
                System.out.println("Erro ao atualizar.");
            }
        } else {
            System.out.println("ID inválido.");
        }
    }

    // ── EDITAR PROFESSOR ─────────────────────────────────────────────────
    public static void editarProfessor() {
        List<Professor> lista = sistema.listarProfessores();
        if (lista.isEmpty()) {
            System.out.println("Nenhum professor cadastrado.");
            return;
        }

        for (int i = 0; i < lista.size(); i++) {
            System.out.println("[" + i + "] " + lista.get(i).getNome());
        }

        System.out.print("Digite o ID do professor: ");
        int idx = sc.nextInt();
        sc.nextLine();

        if (idx >= 0 && idx < lista.size()) {
            Professor p = lista.get(idx);
            System.out.print("Novo nome: ");
            p.setNome(sc.nextLine());
            System.out.print("Nova especialização: ");
            p.setEspecializacao(sc.nextLine());
            if (sistema.atualizarProfessor(p)) {
                System.out.println("Professor atualizado!");
            } else {
                System.out.println("Erro ao atualizar.");
            }
        } else {
            System.out.println("N° inválido.");
        }
    }

    // ── CRIAR DEPARTAMENTO ───────────────────────────────────────────────
    public static void criarDepartamento() {
        System.out.print("Nome do departamento: ");
        String nome = sc.nextLine();
        Departamento d = new Departamento(nome);
        if (sistema.cadastrarDepartamento(d)) {
            System.out.println("Departamento criado! ID: " + d.getId());
        } else {
            System.out.println("Erro ao criar departamento.");
        }
    }

    // ── ASSOCIAR ALUNO AO DEPARTAMENTO ──────────────────────────────────
    public static void associarAlunoDepartamento() {
        List<Departamento> deptos = sistema.listarDepartamentos();
        if (deptos.isEmpty()) {
            System.out.println("Nenhum departamento.");
            return;
        }

        System.out.println("\nEscolha o departamento:");
        for (int i = 0; i < deptos.size(); i++) {
            System.out.println(i + " - " + deptos.get(i).getNome());
        }
        int dep = sc.nextInt();
        sc.nextLine();
        Departamento departamento = deptos.get(dep);

        List<Curso> cursos = sistema.listarCursos();
        System.out.println("\nCursos:");
        for (int i = 0; i < cursos.size(); i++) {
            System.out.println(i + " - " + cursos.get(i).getNome());
        }
        int curso = sc.nextInt();
        sc.nextLine();

        List<Aluno> alunos = sistema.listarAlunosPorCurso(cursos.get(curso).getId());
        for (int i = 0; i < alunos.size(); i++) {
            System.out.println("[" + i + "] " + alunos.get(i).getNome());
        }
        System.out.print("ID do aluno: ");
        int alunoIdx = sc.nextInt();
        sc.nextLine();

        Aluno aluno = alunos.get(alunoIdx);
        if (sistema.associarAlunoDepartamento(departamento.getId(), aluno.getId())) {
            System.out.println("Aluno associado!");
        } else {
            System.out.println("Erro ao associar aluno.");
        }
    }

    // ── ASSOCIAR PROFESSOR AO DEPARTAMENTO ──────────────────────────────
    public static void associarProfessorDepartamento() {
        List<Departamento> deptos = sistema.listarDepartamentos();
        if (deptos.isEmpty()) {
            System.out.println("Nenhum departamento.");
            return;
        }

        List<Professor> profs = sistema.listarProfessores();
        if (profs.isEmpty()) {
            System.out.println("Nenhum professor.");
            return;
        }

        System.out.println("\nEscolha o departamento:");
        for (int i = 0; i < deptos.size(); i++) {
            System.out.println(i + " - " + deptos.get(i).getNome());
        }
        int dep = sc.nextInt();
        sc.nextLine();

        System.out.println("\nEscolha o professor:");
        for (int i = 0; i < profs.size(); i++) {
            System.out.println(i + " - " + profs.get(i).getNome());
        }
        int prof = sc.nextInt();
        sc.nextLine();

        if (sistema.associarProfessorDepartamento(deptos.get(dep).getId(), profs.get(prof).getId())) {
            System.out.println("Professor associado!");
        } else {
            System.out.println("Erro ao associar professor.");
        }
    }

    // ── LISTAR DEPARTAMENTOS ─────────────────────────────────────────────
    public static void listarDepartamentos() {
        List<Departamento> deptos = sistema.listarDepartamentos();
        if (deptos.isEmpty()) {
            System.out.println("Nenhum departamento.");
            return;
        }
        for (Departamento d : deptos) {
            d.listarDepartamento();
        }
    }
}
