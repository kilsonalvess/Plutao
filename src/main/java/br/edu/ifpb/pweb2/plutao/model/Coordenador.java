package br.edu.ifpb.pweb2.plutao.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Coordenador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank(message="Campo obrigatório!")
    private String curso;

    @OneToOne
    @JoinColumn(name="professor")
    private Professor professor;

    public Coordenador(Professor professor, String curso){
        this.professor = professor;
        this.curso = curso;
    }

    public void delegarProcesso(Processo processo, Professor professor){
        professor.adicionarProcesso(processo);
    }

}
