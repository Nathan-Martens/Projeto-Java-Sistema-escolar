package models;

import java.util.ArrayList;

public class Departamento {

    private int id;
    private String nome;
    private ArrayList<Aluno> alunos;
    private ArrayList<Professor> professores;

    public Departamento(String nome) {
        this.nome = nome;
        this.alunos = new ArrayList<>();
        this.professores = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public ArrayList<Aluno> getAlunos() {
        return alunos;
    }

    public ArrayList<Professor> getProfessores() {
        return professores;
    }

    public void adicionarAluno(Aluno aluno) {
        alunos.add(aluno);
    }

    public void adicionarProfessor(Professor prof) {
        professores.add(prof);
    }

    public void listarDepartamento() {
        System.out.println("\n===== DEPARTAMENTO: " + nome + " =====");

        System.out.println("\n--- Professores ---");
        if (professores.isEmpty()) {
            System.out.println("Nenhum professor.");
        } else {
            for (Professor p : professores) {
                System.out.println(p.getNome() + " | Especialização: " + p.getEspecializacao());
            }
        }

        System.out.println("\n--- Alunos ---");
        if (alunos.isEmpty()) {
            System.out.println("Nenhum aluno.");
        } else {
            for (Aluno a : alunos) {
                System.out.println(a.getNome() + " | RA: " + a.getRaDoAluno()
                        + " | Curso: " + a.getCurso().getNome());
            }
        }
    }
}
