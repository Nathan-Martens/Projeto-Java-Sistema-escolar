package models;

import java.util.ArrayList;

public class Curso {

    private int id;
    private String nome;
    private ArrayList<Aluno> alunos;

    public Curso(String nome) {
        this.nome = nome;
        this.alunos = new ArrayList<>();
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

    public void adicionarAluno(Aluno aluno) {
        alunos.add(aluno);
        aluno.setCurso(this);
    }

    public void listarAlunos() {
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
