package br.edu.ifpb.pweb2.plutao.model;

import br.edu.ifpb.pweb2.plutao.enums.StatusProcesso;
import br.edu.ifpb.pweb2.plutao.enums.TipoDecisao;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
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
    private Integer id;

    @NotBlank(message = "Campo obrigatório")
    private String numero;

    @NotBlank(message = "Campo obrigatório")
    private String requerimento;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dataCriacao;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dataDistribuicao;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dataParecer;

    private boolean parecer;

    private byte[] documento;

    @Enumerated(EnumType.STRING)
    private StatusProcesso estado;

    @Enumerated(EnumType.STRING)
    private TipoDecisao decisaoRelator;

    @ManyToOne
    private Professor relator;

    @ManyToOne
    private Aluno aluno;

    @ManyToOne
    private Colegiado colegiado;

    @ManyToOne
    @JoinColumn(name = "assunto")
    private Assunto assunto;

    @ManyToOne
    private Reuniao emPauta;

    @OneToMany(mappedBy = "processo")
    private List<Voto> votos = new ArrayList<>();

    public Processo( Aluno aluno, Assunto assunto, String textoRequerimento, Colegiado colegiado) {
        this.aluno = aluno;
        this.numero = Integer.toString(this.id);
        this.estado = StatusProcesso.CRIADO;
        this.dataCriacao = new Date();
        this.assunto = assunto;
        this.requerimento = textoRequerimento;
        this.colegiado = colegiado;
    }

    public Processo(Aluno aluno,Assunto assunto){
        this.aluno = aluno;
        this.assunto = assunto;
    }
}
