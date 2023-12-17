package br.edu.ifpb.pweb2.plutao.model;

import br.edu.ifpb.pweb2.plutao.enums.StatusReuniao;
import jakarta.persistence.*;
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
public class Reuniao{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull(message="Campo obrigatório!")
    private Date dataReuniao;

    @Enumerated(EnumType.STRING)
    private StatusReuniao reuniao;

    @ManyToOne
    private Colegiado colegiado;

    @OneToMany
    private List<Processo> processos = new ArrayList<>();

    public Reuniao(Colegiado colegiado,List<Processo> processos){
        this.colegiado = colegiado;
        this.processos = processos;
        this.reuniao = StatusReuniao.PROGRAMADA;
    }

    public void addProcesso(Processo processo) {
        this.processos.add(processo);

    }

    @Override
    public String toString(){
        return "Reunião de "+ this.colegiado+" - "+ this.dataReuniao;
    }
}
