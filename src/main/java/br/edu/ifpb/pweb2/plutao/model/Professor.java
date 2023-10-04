package br.edu.ifpb.pweb2.plutao.model;

import br.edu.ifpb.pweb2.plutao.model.Colegiado;
import jakarta.persistence.*;
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
    private int id;

    private String nome;

    private String telefone;

    private String matricula;

    private String login;

    private String senha;

    boolean coordenador;

    @OneToMany
    private List<Colegiado> colegiados = new ArrayList<>();

    @OneToOne
    private Voto voto;

    @OneToMany
    private List<Processo> processos = new ArrayList<>();
}
