package br.edu.ifpb.pweb2.plutao.model;

import br.edu.ifpb.pweb2.plutao.enums.TipoVoto;
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

    private boolean ausente;

    @Enumerated(EnumType.ORDINAL)
    private TipoVoto voto;

    @OneToOne
    private Professor professor;

    @ManyToOne
    private Processo processo;
}