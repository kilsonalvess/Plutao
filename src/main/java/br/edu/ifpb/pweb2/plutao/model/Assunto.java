package br.edu.ifpb.pweb2.plutao.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.BindingResult;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Assunto{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nome;

    @OneToMany
    private List<Processo> processos;

}
