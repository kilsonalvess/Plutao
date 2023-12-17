package br.edu.ifpb.pweb2.plutao.model;

import br.edu.ifpb.pweb2.plutao.model.Colegiado;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Professor{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "Campo obrigatório")
    private String nome;

    @NotBlank(message = "Campo obrigatório")
    private String telefone;

    @NotBlank(message = "Campo obrigatório")
    private String matricula;

    @NotBlank(message = "Campo obrigatório")
    private String login;

    @NotBlank(message = "Campo obrigatório")
    @Size(min = 3, max = 60, message = "Senha deve ter entre 3 e 60 caracteres")
    private String senha;

    @ManyToMany(mappedBy = "membros")
    private List<Colegiado> colegiados;

    @OneToMany(mappedBy = "relator")
    private List<Processo> processos = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "curso")
    protected Curso curso;

    public Professor(int id, String nome, String fone, String matricula, String login, String senha){
        this.id = id;
        this.nome = nome;
        this.telefone = fone;
        this.matricula = matricula;
        this.login = login;
        this.senha = senha;
    }

    public void adicionarProcesso(Processo processo){
        this.processos.add(processo);
    }

    public void adicionarColegiado(Colegiado colegiado){
        this.colegiados.add(colegiado);
    }

    @Override
    public String toString(){
        return this.nome;
    }
}
