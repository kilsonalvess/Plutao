package br.edu.ifpb.pweb2.plutao.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
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

    @NotBlank(message = "Campo nome Obrigatório!")
    private String nome;

    private String telefone;

    @NotBlank(message = "Campo matrícula Obrigatório!")
    private String matricula;

    @NotBlank(message = "Campo login Obrigatório!")
    private String login;

    @NotBlank(message = "Campo senha Obrigatório!")
    private String senha;

    @OneToMany
    private List<Processo> processos = new ArrayList<>();

    @OneToOne
    private Colegiado colegiado;
}