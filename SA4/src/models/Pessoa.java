package models;

public abstract class Pessoa {
    private int id;
    private String nome;
    private String telefone;
    private String endereco;
    private String cpf;
    private String estadoCivil;
    private String email;
    private String dataNasc;

    public Pessoa(String nome, String telefone, String endereco, String cpf,
                  String estadoCivil, String email, String dataNasc) {
        this.nome = nome;
        this.telefone = telefone;
        this.endereco = endereco;
        this.cpf = cpf;
        this.estadoCivil = estadoCivil;
        this.email = email;
        this.dataNasc = dataNasc;
    }

    public abstract String exibirDados();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String n) {
        this.nome = n;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String t) {
        this.telefone = t;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String e) {
        this.endereco = e;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String c) {
        this.cpf = c;
    }

    public String getEstadoCivil() {
        return estadoCivil;
    }

    public void setEstadoCivil(String e) {
        this.estadoCivil = e;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String e) {
        this.email = e;
    }

    public String getDataNasc() {
        return dataNasc;
    }

    public void setDataNasc(String d) {
        this.dataNasc = d;
    }
}
