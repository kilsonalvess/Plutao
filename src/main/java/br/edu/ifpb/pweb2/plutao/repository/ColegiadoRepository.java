package br.edu.ifpb.pweb2.plutao.repository;

import br.edu.ifpb.pweb2.plutao.model.Colegiado;
import br.edu.ifpb.pweb2.plutao.model.Coordenador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ColegiadoRepository extends JpaRepository<Colegiado, Integer> {
    List<Colegiado> findByCoordenador(Coordenador coordenador);
}
