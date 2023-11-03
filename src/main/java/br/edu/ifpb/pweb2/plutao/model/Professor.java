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

    private boolean coordenador;

    @ManyToMany(mappedBy = "membros")
    private List<Colegiado> colegiados;

    @OneToMany(mappedBy = "")
    private List<Processo> processos = new ArrayList<>();

    @OneToOne
    private Voto voto;
}
