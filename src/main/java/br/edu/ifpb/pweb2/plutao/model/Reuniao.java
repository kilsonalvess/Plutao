package br.edu.ifpb.pweb2.plutao.model;

import br.edu.ifpb.pweb2.plutao.enums.StatusReuniao;
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
public class Reuniao{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dataReuniao;

    @Enumerated(EnumType.ORDINAL)
    private StatusReuniao reuniao;

    private byte[] ata;

    @ManyToOne
    private Colegiado colegiado;

    @OneToMany
    @JoinColumn(name = "reuniao_id")
    private List<Processo> processos = new ArrayList<>();

    public void addProcesso(Processo processo) {
        this.processos.add(processo);

    }
}
