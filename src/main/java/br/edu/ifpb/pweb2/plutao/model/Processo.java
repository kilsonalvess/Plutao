package br.edu.ifpb.pweb2.plutao.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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

    @NotBlank(message = "Campo número obrigatório!")
    private String numero;

    @NotBlank(message = "Campo requerimento obrigatório!")
    private String requerimento;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dataRecepecao;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dataDistribuicao;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dataParecer;

    private byte[] parecer;

    @Enumerated(EnumType.ORDINAL)
    private TipoDecisao decisaoRelator;

    @ManyToOne
    @JoinColumn(name = "professor_id")
    private Professor relator;

    @ManyToOne
    @JoinColumn(name = "aluno_id")
    private Aluno interessado;

    @OneToOne(cascade=CascadeType.PERSIST)
    @JoinColumn(name = "assunto_id")
    private Assunto assunto;

    @ManyToOne
    private Reuniao emPauta;

    @OneToMany
    @JoinColumn(name = "voto_id")
    private List<Voto> votos = new ArrayList<>();

    public Processo(Aluno aluno){
        this.interessado = aluno;
    }
}
