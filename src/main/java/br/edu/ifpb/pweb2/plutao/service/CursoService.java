package br.edu.ifpb.pweb2.plutao.service;

import java.util.List;
import java.util.Optional;

import br.edu.ifpb.pweb2.plutao.model.Professor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import br.edu.ifpb.pweb2.plutao.model.Curso;
import br.edu.ifpb.pweb2.plutao.repository.CursoRepository;

@Component
public class CursoService implements Service<Curso, Integer> {

    @Autowired
    private CursoRepository cursoRepository;


    @Override
    public Page<Curso> findAll(Pageable p) {
        return cursoRepository.findAll(p);
    }

    @Override
    public Curso findById(Integer id) {
        Curso curso = null;
        Optional<Curso> opCurso = cursoRepository.findById(id);
        if (opCurso.isPresent()) {
            curso = opCurso.get();
        }
        return curso;
    }

    @Override
    public Curso save(Curso curso) {
        return cursoRepository.save(curso);
    }

    @Override
    public void deleteById(Integer id) {
        cursoRepository.deleteById(id);
    }

    public List<Curso> getCursos(){
        return this.cursoRepository.findAll();
    }

    public Curso getCursoPorId(Integer id){
        return this.cursoRepository.findById(id).orElse(null);
    }

}