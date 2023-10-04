package br.edu.ifpb.pweb2.plutao.model;

import br.edu.ifpb.pweb2.plutao.model.TipoVoto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Voto{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private TipoVoto voto;

    private boolean ausente;

    @ManyToOne
    private Professor professor;

    @OneToOne
    private Processo processo;
}