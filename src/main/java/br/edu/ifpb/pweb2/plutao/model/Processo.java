package br.edu.ifpb.pweb2.plutao.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Processo{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String numero;

    private String requerimento;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date dataRecepecao;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date dataDistribuicao;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date dataParecer;

    private byte[] parecer;

    private TipoDecisao decisaoRelator;

    @ManyToOne
    private Professor relator;

    @ManyToOne
    @JoinColumn(name = "estudante_id")
    private Aluno aluno;

    @OneToOne(cascade=CascadeType.PERSIST)
    @JoinColumn(name = "assunto_id")
    private Assunto assunto;

    @ManyToOne
    private Reuniao emPauta;

    @OneToMany
    private List<Voto> votos = new ArrayList<>();

    public Processo(Aluno aluno){
        this.aluno = aluno;
    }
}
