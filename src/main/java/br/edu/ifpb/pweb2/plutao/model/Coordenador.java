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
    private Integer id;

    @OneToOne
    @JoinColumn(name="professor")
    private Professor professor;

    public void delegarProcesso(Processo processo, Professor professor){
        professor.adicionarProcesso(processo);
    }

    @Override
    public String toString(){
        return this.professor.toString() + " - " + this.professor.curso;
    }

}
