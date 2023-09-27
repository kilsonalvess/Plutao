package br.edu.ifpb.pweb2.plutao.repository;

import br.edu.ifpb.pweb2.plutao.model.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlunoRepository extends JpaRepository<Aluno, Integer> {

}
