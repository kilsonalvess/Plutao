package br.edu.ifpb.pweb2.plutao.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Processo{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String numero;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date dataRecepecao;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date dataDistribuicao;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date dataParecer;

    private byte[] parecer;

    private TipoDecisao decisaoRelator;

    @ManyToOne
    @JoinColumn(name = "estudante_id")
    private Aluno aluno;

    @OneToOne
    private Assunto assunto;
}
