package br.edu.ifpb.pweb2.plutao.model;

import jakarta.persistence.*;
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

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date dataInicio;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date dataFim;

    private String descricao;

    private String portaria;

    private String curso;

    @OneToMany
    private List<Professor> membros = new ArrayList<>();

    @OneToMany(mappedBy = "colegiado")
    private List<Reuniao> reunioes = new ArrayList<>();

    @OneToOne
    private Aluno aluno;
}
