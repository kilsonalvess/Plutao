package br.com.ifpb.plutao.project;

public class Aluno {
    //* Atributos da classe aluno
    private int id;
    private String nome;
    private String telefone;
    private String matricula;
    private String login;
    private String senha;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public void RelatorioDeAluno (){
        System.out.println("id:"+id);
        System.out.println("Nome:"+nome);
        System.out.println("Telefone"+telefone);
        System.out.println("Matricula:"+matricula);
        System.out.println("Login:"+login);
        System.out.println("Senha:"+senha);



    }

}