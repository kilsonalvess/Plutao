package br.edu.ifpb.pweb2.plutao.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Colegiado{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dataInicio;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Future(message = "Data deve ser futura")
    private Date dataFim;

    @NotBlank(message = "Campo obrigatório")
    private String descricao;

    private String portaria;

    @OneToOne
    @JoinColumn(name = "curso")
    private Curso curso;

    @OneToOne
    @JoinColumn(name="coordenador")
    private Coordenador coordenador;

    @ManyToMany
    private List<Professor> membros = new ArrayList<>();

    @OneToMany(mappedBy = "colegiado")
    private List<Processo> processos;

    @OneToMany(mappedBy = "colegiado")
    private List<Reuniao> reunioes = new ArrayList<>();

    public Colegiado(Date dataInicio, Date dataFim, String descricao, String portaria, Curso curso) {
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.descricao = descricao;
        this.portaria = portaria;
        this.curso = curso;
    }

    public Colegiado(List<Professor> professores){
        this.membros = professores;
    }

    @Override
    public String toString(){
        return "Colegiado de " + this.curso;
    }

    public void adicionarReuniao(Reuniao reuniao){
        this.reunioes.add(reuniao);
    }

    public void adicionarProcesso(Processo processo){
        this.processos.add(processo);
    }


}
