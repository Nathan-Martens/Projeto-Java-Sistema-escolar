package models;

public class Aluno extends Pessoa {

    private String raDoAluno;
    private String historicoAluno;
    private int ativo;
    private Curso curso;

    public Aluno(String nome, String telefone, String endereco, String cpf,
                 String estadoCivil, String email, String dataNasc,
                 String raDoAluno, String historicoAluno, int ativo, Curso curso) {
        super(nome, telefone, endereco, cpf, estadoCivil, email, dataNasc);
        this.raDoAluno = raDoAluno;
        this.historicoAluno = historicoAluno;
        this.ativo = ativo;
        this.curso = curso;
    }

    public String getRaDoAluno() {
        return raDoAluno;
    }

    public void setRaDoAluno(String ra) {
        this.raDoAluno = ra;
    }

    public String getHistoricoAluno() {
        return historicoAluno;
    }

    public void setHistoricoAluno(String h) {
        this.historicoAluno = h;
    }

    public int getAtivo() {
        return ativo;
    }

    public void setAtivo(int a) {
        this.ativo = a;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso c) {
        this.curso = c;
    }

    @Override
    public String exibirDados() {
        return "Nome: " + getNome() + "\n" +
                "Telefone: " + getTelefone() + "\n" +
                "Endereço: " + getEndereco() + "\n" +
                "CPF: " + getCpf() + "\n" +
                "Estado Civil: " + getEstadoCivil() + "\n" +
                "Email: " + getEmail() + "\n" +
                "Data de Nascimento: " + getDataNasc() + "\n" +
                "RA do Aluno: " + getRaDoAluno() + "\n" +
                "Histórico: " + getHistoricoAluno() + "\n" +
                "Ativo: " + (getAtivo() == 1 ? "Sim" : "Não") + "\n" +
                "Curso: " + (getCurso() != null ? getCurso().getNome() : "Nenhum");
    }
}
