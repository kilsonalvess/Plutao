package br.edu.ifpb.pweb2.plutao.repository;

import br.edu.ifpb.pweb2.plutao.model.Assunto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssuntoRepository extends JpaRepository<Assunto, Integer> {

}
