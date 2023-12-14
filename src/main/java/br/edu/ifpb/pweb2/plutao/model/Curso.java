package br.edu.ifpb.pweb2.plutao.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.NotBlank;

@NoArgsConstructor
@Data
@Entity
public class Curso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank(message="Campo obrigatório!")
    private String nome;

    public Curso(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString(){
        return this.nome;
    }

}