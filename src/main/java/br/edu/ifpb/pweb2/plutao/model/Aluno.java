package br.edu.ifpb.pweb2.plutao.model;


import jakarta.persistence.*;
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

    private String nome;

    private String telefone;

    private String matricula;

    private String login;

    private String senha;

    @OneToMany
    private List<Processo> processos = new ArrayList<>();

    @OneToOne
    private Colegiado colegiado;
}