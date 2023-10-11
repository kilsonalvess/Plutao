package br.edu.ifpb.pweb2.plutao.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.BindingResult;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Assunto{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String nome;

    @OneToOne
    private Processo processo;

    public Assunto(String nome) {
        this.nome = nome;
    }
}
