package br.edu.ifpb.pweb2.plutao.repository;

import br.edu.ifpb.pweb2.plutao.model.Aluno;
import br.edu.ifpb.pweb2.plutao.model.Processo;
import br.edu.ifpb.pweb2.plutao.model.Professor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProcessoRepository extends JpaRepository<Processo, Integer> {
    List<Processo> findByParecerAndAluno(boolean status, Aluno aluno);

    List<Processo> findByRelator(Professor professor);

    List<Processo> findAllByAlunoId(Integer id);

    List<Processo> findByAluno(Aluno aluno);
}
