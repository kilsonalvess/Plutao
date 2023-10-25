package br.edu.ifpb.pweb2.plutao.repository;

import br.edu.ifpb.pweb2.plutao.model.Aluno;
import br.edu.ifpb.pweb2.plutao.model.Processo;
import br.edu.ifpb.pweb2.plutao.model.Professor;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProcessoRepository extends JpaRepository<Processo, Integer> {

    List<Processo> findByParecerAndInteressado(boolean status, Aluno aluno);

    List<Processo> findByRelator(Professor professor);
}
