package br.edu.ifpb.pweb2.plutao.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.ifpb.pweb2.plutao.model.Curso;
import java.lang.Long;

public interface CursoRepository extends JpaRepository<Curso, Integer>{

}