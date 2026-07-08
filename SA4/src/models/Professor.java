package models;

public class Professor extends Pessoa {

    private String especializacao;
    private int ativo;

    public Professor(String nome, String telefone, String endereco, String cpf,
                     String estadoCivil, String email, String dataNasc,
                     String especializacao, int ativo) {
        super(nome, telefone, endereco, cpf, estadoCivil, email, dataNasc);
        this.especializacao = especializacao;
        this.ativo = ativo;
    }

    public String getEspecializacao() {
        return especializacao;
    }

    public void setEspecializacao(String e) {
        this.especializacao = e;
    }

    public int getAtivo() {
        return ativo;
    }

    public void setAtivo(int a) {
        this.ativo = a;
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
                "Especialização: " + getEspecializacao() + "\n" +
                "Ativo: " + (getAtivo() == 1 ? "Sim" : "Não");
    }
}
