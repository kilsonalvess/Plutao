package br.edu.ifpb.pweb2.plutao.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Aluno implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "Campo obrigatório")
    private String nome;

    private String telefone;

    @NotBlank(message = "Campo obrigatório")
    private String matricula;

    @NotBlank(message = "Campo obrigatório")
    private String login;

    @NotBlank(message = "Campo obrigatório")
    @Size(min = 3, max = 60, message = "Senha deve ter entre 3 e 60 caracteres")
    private String senha;

    @OneToMany
    private List<Processo> processos = new ArrayList<>();

    @OneToOne
    private Colegiado colegiado;

    public Aluno(String nome, String fone, String matricula, String login, String senha) {
        this.nome = nome;
        this.telefone = fone;
        this.matricula = matricula;
        this.login = login;
        this.senha = senha;
    }

    public void adicionarProcesso(Processo processo){
        this.processos.add(processo);
    }
}