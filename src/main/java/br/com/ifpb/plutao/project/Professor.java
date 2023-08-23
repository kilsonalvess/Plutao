package br.com.ifpb.plutao.project;

public class Professor extends Aluno {

    private int id;
    private String nome;
    private String telefone;
    private String matricula;
    private String login;
    private String senha;
    boolean coordenador;

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String getNome() {
        return nome;
    }

    @Override
    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String getTelefone() {
        return telefone;
    }

    @Override
    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    @Override
    public String getMatricula() {
        return matricula;
    }

    @Override
    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    @Override
    public String getLogin() {
        return login;
    }

    @Override
    public void setLogin(String login) {
        this.login = login;
    }

    @Override
    public String getSenha() {
        return senha;
    }

    @Override
    public void setSenha(String senha) {
        this.senha = senha;
    }

    public boolean isCoordenador() {
        return coordenador;
    }

    public void setCoordenador(boolean coordenador) {
        this.coordenador = coordenador;
    }

    public void RelatorioProfessor (){
        System.out.println("id:"+id);
        System.out.println("Nome:"+nome);
        System.out.println("Telefone"+telefone);
        System.out.println("Matricula:"+matricula);
        System.out.println("Login:"+login);
        System.out.println("Senha:"+senha);
        System.out.println("Cordenador:"+coordenador);



    }




}
